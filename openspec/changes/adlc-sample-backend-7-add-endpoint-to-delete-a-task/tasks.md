# Tasks

## 1. Implement DELETE Endpoint

- [x] 1.1 Add `@DeleteMapping("/api/tasks/{id}")` method to `TaskController` that removes the task from the map and returns HTTP 204, and verify the code compiles

## 2. Add Tests for Delete Scenarios

- [x] 2.1 Add test for successful deletion returns HTTP 204 and verify it passes with `mvn -B verify`
- [x] 2.2 Add test that deleted task no longer appears in GET /api/tasks and verify it passes with `mvn -B verify`
- [x] 2.3 Add test that deleted task no longer appears in GET /api/tasks with status filter and verify it passes with `mvn -B verify`
- [x] 2.4 Add test for deleting non-existent task returns HTTP 404 and verify it passes with `mvn -B verify`
- [x] 2.5 Add test for deleting same task twice (204 then 404) and verify it passes with `mvn -B verify`
- [x] 2.6 Add test for completing deleted task returns HTTP 404 and verify it passes with `mvn -B verify`

## 3. Integration Verification

- [x] 3.1 Run `mvn -B verify` to confirm all new and existing tests pass and no existing behavior is broken
- [x] 3.2 Verify existing create, list, and complete tests continue to pass
