package com.ouyang.community.service;

import com.ouyang.community.dto.PaginationDTO;
import com.ouyang.community.dto.QuestionDTO;
import com.ouyang.community.mapper.QuestionMapper;
import com.ouyang.community.mapper.UserMapper;
import com.ouyang.community.model.Question;
import com.ouyang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offSet = size*(page-1);
        List<Question> questionList = questionMapper.list(offSet,size);
        List<QuestionDTO>  questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for(Question question:questionList){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        return  paginationDTO;
    }

}
