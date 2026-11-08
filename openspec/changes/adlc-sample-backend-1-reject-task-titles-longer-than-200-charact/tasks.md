# Tasks

## 1. Implement Title Length Validation

- [x] 1.1 Add `@Size(max=200)` annotation to the `title` field in `NewTask` record and verify the code compiles
- [x] 1.2 Verify that `mvn -B verify` passes with the validation in place

## 2. Add Tests for Title-Length Boundaries

- [x] 2.1 Add test case for 200-character title acceptance and verify it passes with `mvn -B verify`
- [x] 2.2 Add test case for 201-character title rejection and verify it passes with `mvn -B verify`
- [x] 2.3 Verify that existing blank-title rejection test continues to pass with `mvn -B verify`

## 3. Integration Verification

- [x] 3.1 Run `mvn -B verify` to confirm all tests pass and no existing behavior is broken
