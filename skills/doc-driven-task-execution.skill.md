# Doc-Driven Task Execution Skill

## 1. Purpose

This skill enforces a document-driven workflow for any task involving design or changes. It prevents direct implementation before design closure and ensures all execution is anchored by stable context, a main plan, executable implementation tasks, and explicit acceptance criteria.

This skill is suitable for:
- feature development
- bug fixes
- refactoring
- schema or field changes
- API/interface changes
- configuration updates
- business rule changes
- documentation updates tied to implementation

This skill is **not required** for pure explanation, pure Q&A, or read-only analysis tasks.

---

## 2. Core Principle

All change-related tasks must follow this closed loop:

1. Build or update stable context
2. Produce the main design plan
3. Break the plan into executable tasks
4. Generate acceptance criteria from the plan and implementation tasks
5. Ask for confirmation before execution
6. Execute only after confirmation
7. After each execution round, update stable context incrementally
8. If deviation is discovered, stop, update the documents, ask for confirmation, then continue

---

## 3. Hard Rules

### Rule 1: No direct implementation
For any change-related task, do not jump directly into coding, editing, or final delivery.

### Rule 2: Four documents are mandatory
Before execution, the following documents must exist and be mutually consistent:
- `context.md`
- `plan.md`
- `implementation.md`
- `acceptance.md`

### Rule 3: Plan must contain both design and landing strategy
`plan.md` must describe both:
- the main design
- how this specific change will be landed

### Rule 4: Implementation must be executable
`implementation.md` must be actionable down to:
- files
- fields
- classes/interfaces
- functions/methods
- tests/docs to be updated

### Rule 5: Acceptance must be three-in-one
`acceptance.md` must cover:
- development acceptance
- testing acceptance
- business acceptance

### Rule 6: Context stores stable information only
`context.md` may contain only durable task memory, including:
- stable business background
- confirmed terminology
- stable constraints
- confirmed decisions
- rejected options
- key boundaries

`context.md` must not store:
- temporary discussion noise
- process chatter
- speculative reasoning
- unstable intermediate findings

### Rule 7: Context must be updated after each execution round
After every execution round, incrementally update `context.md` with:
- new stable information
- confirmed decision changes

### Rule 8: Deviation requires stop-and-confirm
If implementation is found to deviate from `plan.md` or `implementation.md`:
1. stop execution immediately
2. update `plan.md`
3. update `implementation.md`
4. update `acceptance.md` if needed
5. explain the deviation and impact
6. wait for user confirmation
7. continue only after confirmation

No unconfirmed deviation is allowed.

---

## 4. State Machine

### S0. Task Intake
Input a task, goal, constraints, and related context.

Action:
- determine whether the task involves design or change
- if yes, this skill must be used

### S1. Context Build
Create or update `context.md`.

Goal:
- preserve stable task memory
- reduce repeated prompt cost
- anchor later design and implementation

### S2. Main Plan
Create `plan.md`.

Goal:
- define the main design
- define the landing strategy for this task

### S3. Implementation Breakdown
Create `implementation.md`.

Goal:
- break the plan into executable tasks
- make scope and change points explicit

### S4. Acceptance Definition
Create `acceptance.md`.

Goal:
- derive verifiable completion criteria from the plan and implementation

### S5. Confirmation Gate
Wait for user confirmation before execution.

### S6. Execution
Execute according to `implementation.md` under `plan.md` constraints.

After execution:
- update `context.md` with new stable information only
- keep all four documents consistent

### S7. Deviation Handling
If a mismatch is discovered between actual implementation and the documented plan:
- stop
- update affected documents
- explain the deviation
- ask for confirmation
- continue only after confirmation

---

## 5. Document Responsibilities

### context.md
Purpose:
- durable task memory
- stable background and constraints
- reduce repeated context feeding

Recommended sections:
- Task
- Business Background
- Current System Context
- Stable Constraints
- Confirmed Terminology
- Confirmed Decisions
- Rejected Options
- External Dependencies
- Key Boundaries
- Context Update Log

### plan.md
Purpose:
- main design baseline
- landing strategy baseline

Recommended sections:
- Goal
- Scope / Non-scope
- Design Basis
- Main Design
- Change Strategy
- Risks and Trade-offs
- Alternatives
- Final Decision
- Preconditions for Execution

### implementation.md
Purpose:
- executable task breakdown

Recommended sections:
- Execution Strategy
- Task List
- Impacted Tests
- Impacted Docs
- Execution Constraints

Each task should include:
- name
- purpose
- dependencies
- files involved
- classes/interfaces involved
- functions/methods involved
- fields involved
- concrete change
- expected output
- done condition

### acceptance.md
Purpose:
- completion definition
- verification checklist

Recommended sections:
- Acceptance Basis
- Development Acceptance
- Testing Acceptance
- Business Acceptance
- Non-functional Acceptance
- Final Done Definition

---

## 6. Pre-Execution Consistency Check

Before execution, validate the following:
- `plan.md` respects constraints in `context.md`
- `implementation.md` covers all key change points in `plan.md`
- `acceptance.md` covers all tasks in `implementation.md`
- no conflicts exist between the four documents

If inconsistency is found, fix the documents before execution.

---

## 7. Output Behavior

When using this skill:
- be structured and precise
- keep context focused on durable information
- keep the plan architectural and practical at the same time
- keep implementation directly actionable
- keep acceptance verifiable
- state clearly when confirmation is required
- never mark a task complete without acceptance basis

---

## 8. Ready-to-Use Prompt

```text
You are a document-driven task execution agent.

For any task involving design, implementation, code changes, configuration changes, schema changes, interface changes, or documentation changes, you must follow the mandated workflow below.

Workflow:
1. Build or update context.md
2. Produce plan.md
3. Produce implementation.md
4. Produce acceptance.md
5. Ask for confirmation before execution
6. Execute only after confirmation
7. After each execution round, update context.md with stable information only
8. If any implementation deviation from plan.md or implementation.md is discovered, stop execution immediately, update the affected documents, explain the deviation, and wait for user confirmation before continuing

Hard rules:
- Never jump directly into implementation for change-related tasks
- Never claim a task is complete without acceptance.md
- implementation.md must be executable and specific down to files, fields, functions/methods
- acceptance.md must cover development acceptance, testing acceptance, and business acceptance
- context.md must only contain stable background, constraints, confirmed terminology, confirmed decisions, rejected options, and key boundaries
- Do not store temporary discussion noise or process logs in context.md
- Any deviation requires document update first, then user confirmation

Document responsibilities:
- context.md = stable context and durable task memory
- plan.md = main design and change strategy
- implementation.md = executable task breakdown
- acceptance.md = completion criteria derived from plan and implementation

When writing outputs:
- Keep documents structured and precise
- Keep context focused on durable information
- Keep implementation actionable
- Keep acceptance verifiable
- Ensure all documents are mutually consistent
```

---

## 9. Suggested Templates

### context.md

```markdown
# Context

## 1. Task
- Task name:
- Task goal:

## 2. Business Background
- Business background:
- Task source:
- Expected business value:

## 3. Current System Context
- Related modules:
- Related services:
- Related interfaces:
- Related tables/fields:
- Key call chain:

## 4. Stable Constraints
- Non-negotiable constraints:
- Technical constraints:
- Compatibility constraints:
- Performance/security constraints:

## 5. Confirmed Terminology
- Term A:
- Term B:

## 6. Confirmed Decisions
- Confirmed decision 1:
- Confirmed decision 2:

## 7. Rejected Options
- Rejected option 1:
- Reason:
- Rejected option 2:
- Reason:

## 8. External Dependencies
- External systems:
- Third-party dependencies:
- Preconditions:

## 9. Key Boundaries
- In scope:
- Out of scope:

## 10. Context Update Log
- [Round/Date] New stable info:
- [Round/Date] Decision change:
```

### plan.md

```markdown
# Plan

## 1. Goal
- Task goal:
- Success criteria:

## 2. Scope
- In scope:
- Out of scope:

## 3. Design Basis
- Referenced context:
- Key constraints:
- Key assumptions:

## 4. Main Design
- Overall design:
- Module responsibilities:
- Data flow / call chain:
- Interface / data structure changes:
- Config / schema changes:

## 5. Change Strategy
- Landing path:
- Why this path:
- Change order:
- Impact on existing system:

## 6. Risks and Trade-offs
- Risk 1:
- Mitigation:
- Risk 2:
- Mitigation:

## 7. Alternatives
- Alternative 1:
- Rejection reason:
- Alternative 2:
- Rejection reason:

## 8. Final Decision
- Final solution:
- Why chosen:

## 9. Preconditions for Execution
- Required before execution:
```

### implementation.md

```markdown
# Implementation

## 1. Execution Strategy
- Execution order:
- Dependencies:
- Rollback concerns:

## 2. Task List

### Task 1
- Name:
- Purpose:
- Dependencies:
- Files involved:
- Classes/interfaces involved:
- Functions/methods involved:
- Fields involved:
- Concrete change:
- Expected output:
- Done condition:

### Task 2
- Name:
- Purpose:
- Dependencies:
- Files involved:
- Classes/interfaces involved:
- Functions/methods involved:
- Fields involved:
- Concrete change:
- Expected output:
- Done condition:

## 3. Impacted Tests
- New tests required:
- Existing tests to update:
- Regression scope:

## 4. Impacted Docs
- Docs to update:
- Explanations to add:

## 5. Execution Constraints
- Must not touch:
- Must preserve compatibility for:
```

### acceptance.md

```markdown
# Acceptance

## 1. Acceptance Basis
- From Plan:
- From Implementation:

## 2. Development Acceptance
- [ ] All planned changes are implemented
- [ ] Changed files match implementation plan
- [ ] Fields/functions/interfaces are fully updated
- [ ] No unexplained extra intrusion
- [ ] Documentation is updated

## 3. Testing Acceptance

### Normal Cases
- [ ] Case 1:
- [ ] Case 2:

### Boundary Cases
- [ ] Boundary case 1:
- [ ] Boundary case 2:

### Exception Cases
- [ ] Exception case 1:
- [ ] Exception case 2:

### Regression Cases
- [ ] Regression check 1:
- [ ] Regression check 2:

## 4. Business Acceptance
- [ ] Business goal is met
- [ ] Key business rules are satisfied
- [ ] Existing behavior is not broken
- [ ] User-visible behavior matches expectations

## 5. Non-functional Acceptance
- [ ] Performance requirements
- [ ] Security requirements
- [ ] Compatibility requirements
- [ ] Maintainability requirements

## 6. Final Done Definition
- Done definition:
```

---

## 10. Recommended Usage

For simple tasks, keep all four documents concise but still present.

For medium and complex tasks, expand the depth while preserving the same workflow.

The workflow is universal; only the level of elaboration changes.
