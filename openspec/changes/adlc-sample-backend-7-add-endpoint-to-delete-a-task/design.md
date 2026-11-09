# Design

## Context

The task controller currently manages tasks in a `ConcurrentHashMap` for in-memory storage. The existing POST `/api/tasks` endpoint creates tasks, GET `/api/tasks` retrieves them (with optional status filtering), and POST `/api/tasks/{id}/complete` marks them as completed. The completion endpoint uses `computeIfPresent` to safely update the task status while checking existence.

## Goals / Non-Goals

**Goals:**
- Add a DELETE endpoint that removes tasks from the in-memory store.
- Return HTTP 204 for successful deletions with an empty body.
- Return HTTP 404 when attempting to delete a non-existent task.
- Ensure deleted tasks do not appear in subsequent GET requests or status-filtered queries.
- Ensure completing a deleted task returns HTTP 404.

**Non-Goals:**
- Modify task creation, listing, or completion logic.
- Add soft-delete or archival functionality (hard delete only).
- Add authorization or permission checks for deletion.
- Modify storage or add persistence layer.
- Change data model or task attributes.

## Decisions

**Decision: Use HTTP 204 No Content for successful deletion**
- **Rationale:** REST convention for successful delete operations that return no body. Spring Boot's `ResponseEntity.noContent()` maps directly to this.
- **Alternative considered:** HTTP 200 with confirmation body. Rejected because 204 is more idiomatic for DELETE operations.

**Decision: Use `Map.remove()` for in-memory deletion**
- **Rationale:** `ConcurrentHashMap.remove()` is atomic and thread-safe, consistent with the existing use of `computeIfPresent`. Returns the removed value for optional verification.
- **Alternative considered:** Manual check then delete. Rejected due to race condition risk and inconsistency with existing patterns.

**Decision: Return HTTP 404 for non-existent task IDs**
- **Rationale:** REST convention for attempting to operate on a non-existent resource. Consistent with existing complete endpoint behavior.

## Risks / Trade-offs

- **None identified:** The change is localized to a new endpoint, reuses existing patterns (similar to `complete`), and does not modify existing behavior or data structures.

## Migration Plan

- No rollback needed; deletion is not reversible in this in-memory system (as designed).
- Clients must be aware that deleted tasks are permanently removed.
- No schema or data migration required (in-memory store).

## Open Questions

(None)
