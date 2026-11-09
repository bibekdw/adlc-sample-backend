# Proposal

**Story:** bibekdw/adlc-sample-backend#10  
**Change ID:** adlc-sample-backend-10-add-a-priority-filter-to-the-task-list

## Why

The task list API currently supports filtering by `status` but not by `priority`. Adding priority filtering allows clients to view tasks by their importance level, improving usability for task management workflows.

## What Changes

- The `GET /api/tasks` endpoint now accepts an optional `priority` query parameter.
- When `priority` is provided, results are filtered to include only tasks matching that priority level (HIGH, MEDIUM, or LOW).
- The `priority` and `status` parameters can be combined; a task must match both filters if both are specified.
- Without `priority`, the list remains unchanged and returns all tasks filtered by `status` only if given.
- Unsupported priority values (e.g., `priority=URGENT`) are rejected with HTTP 400.
- Results remain ordered by id, ascending.
- Task creation, completion, and deletion behavior remain unchanged.

## Capabilities

### New Capabilities
- `task-list-filtering`: Adds optional query parameter filtering to the task list endpoint, supporting `priority` filter alone or in combination with existing `status` filter.

### Modified Capabilities
(None)

## Impact

- **Affected Code:** `TaskController.java` - GET `/api/tasks` endpoint to accept and apply priority filtering.
- **Affected Tests:** `TaskControllerTest.java` - new tests for priority filtering scenarios.
- **API Impact:** Task list endpoint now accepts optional `priority` query parameter; invalid values return HTTP 400.
- **Out-of-Scope:** No database, persistence, authorization, or other service changes. No changes to create, complete, or delete endpoints.
