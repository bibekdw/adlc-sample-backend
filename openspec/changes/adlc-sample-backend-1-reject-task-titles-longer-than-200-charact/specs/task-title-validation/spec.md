# Spec Delta

## Purpose

Ensures task titles conform to a maximum length constraint, preventing overly long titles that could impact storage efficiency and user experience.

## ADDED Requirements

### Requirement: Task title length maximum
The system SHALL reject task creation when the title exceeds 200 characters and accept titles of exactly 200 characters.

#### Scenario: Title at the limit is accepted
- **WHEN** a task is created with a 200-character title
- **THEN** the system responds with HTTP 201 and includes the task in the response

#### Scenario: Title exceeding the limit is rejected
- **WHEN** a task is created with a 201-character title
- **THEN** the system responds with HTTP 400

#### Scenario: Blank titles continue to be rejected
- **WHEN** a task is created with an empty title
- **THEN** the system responds with HTTP 400
