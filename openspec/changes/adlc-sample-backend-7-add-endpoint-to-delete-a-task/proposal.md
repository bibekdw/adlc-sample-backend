# Proposal

**Story:** bibekdw/adlc-sample-backend#7  
**Change ID:** adlc-sample-backend-7-add-endpoint-to-delete-a-task

## Why

Tasks can be created and completed but never removed. Users cannot clean up tasks they no longer need. A delete endpoint is essential for complete lifecycle management and enables applications to maintain a clean task list.

## What Changes

- Add `DELETE /api/tasks/{id}` endpoint to remove a task.
- Successful deletion returns HTTP 204 with an empty body.
- After deletion, the task no longer appears in `GET /api/tasks` (all filters).
- Attempting to delete a non-existent task returns HTTP 404.
- Completing a deleted task returns HTTP 404.
- All existing create, list, and complete behavior remains unchanged.

## Capabilities

### New Capabilities
- `task-deletion`: Allows clients to permanently remove tasks from the system via DELETE endpoint.

### Modified Capabilities
(None)

## Impact

- **Affected Code:** `TaskController.java` - new DELETE endpoint handler.
- **Affected Tests:** `TaskControllerTest.java` - new tests for delete scenarios.
- **API Impact:** New DELETE `/api/tasks/{id}` endpoint; no changes to existing endpoints.
- **Out-of-Scope:** No database, persistence, authorization, or other service changes. No modifications to task creation, listing, or completion behavior.
