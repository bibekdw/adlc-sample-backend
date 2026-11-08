# Proposal

**Story:** bibekdw/adlc-sample-backend#1  
**Change ID:** adlc-sample-backend-1-reject-task-titles-longer-than-200-charact

## Why

Task titles should have a reasonable length limit to prevent storage inefficiency and UI rendering issues. Enforcing a 200-character maximum provides a clear boundary while allowing descriptive task titles.

## What Changes

- Task creation now validates that the title does not exceed 200 characters.
- Requests with titles longer than 200 characters are rejected with HTTP 400.
- Titles of exactly 200 characters continue to be accepted with HTTP 201.
- Blank title rejection remains in place (existing behavior).
- All other task creation and completion behavior remains unchanged.

## Capabilities

### New Capabilities
- `task-title-validation`: Validates task title length constraints on creation, ensuring titles do not exceed 200 characters while accepting the boundary value.

### Modified Capabilities
(None)

## Impact

- **Affected Code:** `TaskController.java` - POST `/api/tasks` endpoint and `NewTask` request validation.
- **Affected Tests:** `TaskControllerTest.java` - new tests for title-length boundaries.
- **API Impact:** Task creation endpoint now enforces title-length validation; requests exceeding 200 characters return HTTP 400.
- **Out-of-Scope:** No database, persistence, authorization, or other service changes.
