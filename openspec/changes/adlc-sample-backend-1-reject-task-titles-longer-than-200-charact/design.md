# Design

## Context

The task creation endpoint validates the `NewTask` request using Jakarta validation annotations. Currently, the `title` field is annotated with `@NotBlank` to reject empty titles. The validation framework is already in place and integrated with Spring Boot's error handling.

## Goals / Non-Goals

**Goals:**
- Add a maximum length constraint to task titles (200 characters).
- Leverage existing Jakarta validation framework to enforce the constraint.
- Maintain backward compatibility for valid titles.
- Return HTTP 400 for titles exceeding 200 characters.

**Non-Goals:**
- Modify task storage or persistence layer (in-memory storage only).
- Add authorization or user-specific title validation.
- Change any other task attributes or endpoints.
- Modify the task completion or retrieval behavior.

## Decisions

**Decision: Use Jakarta `@Size` annotation for length validation**
- **Rationale:** Jakarta validation annotations are already in use for `@NotBlank`. Adding `@Size(max=200)` is consistent with existing patterns and requires no additional framework dependencies.
- **Alternative considered:** Custom validator annotation. Rejected because `@Size` is simpler and sufficient.

**Decision: Reject long titles with HTTP 400**
- **Rationale:** HTTP 400 (Bad Request) is appropriate for invalid input according to REST conventions. Spring Boot automatically returns 400 for validation failures.

## Risks / Trade-offs

- **None identified:** The change is localized to request validation and leverages proven, standard frameworks.
