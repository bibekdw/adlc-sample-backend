# Design

## Context

The `GET /api/tasks` endpoint currently accepts an optional `status` query parameter to filter tasks by their completion status. The `TaskController.all()` method uses a stream filter to apply this constraint. Priority filtering follows the same pattern - it should be additive, allowing both filters to work independently or together.

The `Task` record already includes a `priority` field (HIGH, MEDIUM, LOW), set during creation and immutable throughout the task's lifetime.

## Goals / Non-Goals

**Goals:**
- Add a `priority` query parameter to the existing `GET /api/tasks` endpoint.
- Support all three priority values: HIGH, MEDIUM, LOW.
- Allow `priority` and `status` to be combined in a single request.
- Reject unsupported priority values with HTTP 400.
- Preserve existing behavior when no priority filter is provided.
- Maintain id ascending order in all filtered results.

**Non-Goals:**
- No changes to task creation, completion, or deletion endpoints.
- No database or persistence layer changes (in-memory map unchanged).
- No authorization or authentication logic.
- No changes to the Priority enum itself.

## Decisions

**Decision 1: Filter implementation location**
- *Chosen:* Add priority filtering logic directly in `TaskController.all()` using a second stream filter condition.
- *Rationale:* Mirrors the existing status filter pattern, keeps logic simple and localized, and avoids introducing new classes or methods.
- *Alternative considered:* Extract filtering logic into a service layer; rejected because the demo is intentionally in-memory and simple.

**Decision 2: Parameter validation**
- *Chosen:* Use Spring's request parameter binding with a `Priority` enum type, allowing Spring to reject invalid values automatically with HTTP 400.
- *Rationale:* Leverages existing Spring validation framework and reduces custom validation code.
- *Alternative considered:* Manual string parsing and validation; rejected as it duplicates Spring's capabilities.

**Decision 3: Ordering guarantee**
- *Chosen:* Rely on the existing `sorted()` call in the stream pipeline to preserve id order after both filters are applied.
- *Rationale:* The sort happens after all filters, so order is preserved regardless of which filters are active.

## Risks / Trade-offs

**Risk 1 - Case sensitivity:** Priority enum values are uppercase (HIGH, MEDIUM, LOW). Spring's enum binding is case-sensitive by default.
- *Mitigation:* Document that priority values must be uppercase in the API; add tests to verify case-sensitive rejection.

**Risk 2 - Performance with large datasets:** The current in-memory implementation streams all tasks into memory. This is acceptable for the demo but would need optimization in production.
- *Mitigation:* Not applicable to this demo; noted as future consideration if task volume grows.

## Migration Plan

No migration required; this is purely additive. Existing clients that do not use the `priority` parameter continue to work as before.

## Open Questions

None; all requirements are clear from the acceptance criteria.
