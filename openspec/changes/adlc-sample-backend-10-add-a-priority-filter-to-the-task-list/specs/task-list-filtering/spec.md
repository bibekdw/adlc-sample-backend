# Spec Delta

## Purpose

Allows clients to filter the task list by priority level, enabling better task management and organization by importance.

## ADDED Requirements

### Requirement: Filter tasks by priority
The system SHALL filter the task list by an optional `priority` query parameter, accepting HIGH, MEDIUM, or LOW values and rejecting unsupported values with HTTP 400.

#### Scenario: Priority filter returns only HIGH tasks
- **WHEN** a request is made to GET /api/tasks?priority=HIGH
- **THEN** the system responds with HTTP 200 and the response includes only tasks with priority HIGH

#### Scenario: Priority filter returns only MEDIUM tasks
- **WHEN** a request is made to GET /api/tasks?priority=MEDIUM
- **THEN** the system responds with HTTP 200 and the response includes only tasks with priority MEDIUM

#### Scenario: Priority filter returns only LOW tasks
- **WHEN** a request is made to GET /api/tasks?priority=LOW
- **THEN** the system responds with HTTP 200 and the response includes only tasks with priority LOW

#### Scenario: Unsupported priority value is rejected
- **WHEN** a request is made to GET /api/tasks?priority=URGENT
- **THEN** the system responds with HTTP 400

### Requirement: Combine priority and status filters
The system SHALL apply both `priority` and `status` filters when both query parameters are provided, returning only tasks that match both criteria.

#### Scenario: Priority and status filters are combined
- **WHEN** a request is made to GET /api/tasks?priority=HIGH&status=PENDING
- **THEN** the system responds with HTTP 200 and the response includes only tasks with priority HIGH and status PENDING

#### Scenario: Combined filters with no matching results
- **WHEN** a request is made to GET /api/tasks?priority=HIGH&status=COMPLETED and no completed HIGH-priority tasks exist
- **THEN** the system responds with HTTP 200 with an empty list

### Requirement: Priority filter is optional
The system SHALL return all tasks (filtered by status if provided) when the priority parameter is not supplied, preserving existing behavior.

#### Scenario: No priority filter returns all tasks
- **WHEN** a request is made to GET /api/tasks without a priority parameter
- **THEN** the system responds with HTTP 200 and the response includes all tasks in the system

#### Scenario: Status filter alone continues to work
- **WHEN** a request is made to GET /api/tasks?status=PENDING without a priority parameter
- **THEN** the system responds with HTTP 200 and the response includes all PENDING tasks

### Requirement: Results remain ordered by id ascending
The system SHALL maintain id ascending order in filtered results.

#### Scenario: Filtered results are sorted by id ascending
- **WHEN** a request is made to GET /api/tasks?priority=HIGH with multiple HIGH-priority tasks
- **THEN** the system responds with HTTP 200 and the tasks are ordered by id in ascending order
