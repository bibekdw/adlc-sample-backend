# Tasks

## 1. Add Priority Filter Parameter

- [ ] 1.1 Add optional `Priority priority` parameter to the `TaskController.all()` method signature and verify the code compiles
- [ ] 1.2 Add a second stream filter condition to filter tasks by priority (only when priority is not null) and verify the code compiles

## 2. Add Tests for Priority Filtering

- [ ] 2.1 Add test for HIGH priority filter and verify it passes with `mvn -B verify`
- [ ] 2.2 Add test for MEDIUM priority filter and verify it passes with `mvn -B verify`
- [ ] 2.3 Add test for LOW priority filter and verify it passes with `mvn -B verify`
- [ ] 2.4 Add test for unsupported priority value (e.g., URGENT) rejection and verify it passes with `mvn -B verify`

## 3. Add Tests for Combined Filters

- [ ] 3.1 Add test for priority and status combined filters and verify it passes with `mvn -B verify`
- [ ] 3.2 Add test for combined filters with no matching results and verify it passes with `mvn -B verify`

## 4. Add Tests for Optional Filter Behavior

- [ ] 4.1 Add test that priority filter is optional (no priority parameter) and verify it passes with `mvn -B verify`
- [ ] 4.2 Add test that status filter alone continues to work and verify it passes with `mvn -B verify`

## 5. Add Tests for Ordering

- [ ] 5.1 Add test for filtered results ordered by id ascending and verify it passes with `mvn -B verify`

## 6. Integration Verification

- [ ] 6.1 Run `mvn -B verify` to confirm all tests pass, including existing tests for task creation, completion, and deletion
- [ ] 6.2 Verify that existing API behavior (task creation with default priority, task completion, task deletion) is unchanged
