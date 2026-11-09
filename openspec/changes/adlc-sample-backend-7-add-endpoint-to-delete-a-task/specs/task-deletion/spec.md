# Spec Delta

## Purpose

Provides clients with the ability to remove tasks from the system, enabling complete task lifecycle management and allowing users to maintain clean, relevant task lists.

## ADDED Requirements

### Requirement: Delete existing task
The system SHALL delete an existing task and return HTTP 204 with an empty body.

#### Scenario: Successful deletion of existing task
- **WHEN** a DELETE request is made for an existing task ID
- **THEN** the system responds with HTTP 204 and an empty body

#### Scenario: Deleted task no longer appears in list
- **WHEN** a task is deleted and GET /api/tasks is called
- **THEN** the deleted task does not appear in the response list

#### Scenario: Deleted task no longer appears in filtered list
- **WHEN** a task is deleted and GET /api/tasks is called with a status filter
- **THEN** the deleted task does not appear in the filtered response

### Requirement: Delete non-existent task
The system SHALL return HTTP 404 when attempting to delete a task ID that does not exist.

#### Scenario: Deleting non-existent task returns 404
- **WHEN** a DELETE request is made for an unknown task ID
- **THEN** the system responds with HTTP 404

#### Scenario: Deleting same task twice
- **WHEN** a task is deleted successfully, then deleted again
- **THEN** the first deletion returns HTTP 204 and the second returns HTTP 404

### Requirement: Complete deleted task
The system SHALL return HTTP 404 when attempting to complete a task that has been deleted.

#### Scenario: Completing deleted task returns 404
- **WHEN** a task is deleted and POST /api/tasks/{id}/complete is called
- **THEN** the system responds with HTTP 404

### Requirement: Existing behavior unchanged
The system SHALL maintain all existing create, list, and complete task behavior.

#### Scenario: Task creation continues to work
- **WHEN** a POST request is made to /api/tasks with valid data
- **THEN** the task is created and returned with HTTP 201

#### Scenario: Task listing continues to work
- **WHEN** a GET request is made to /api/tasks
- **THEN** all existing tasks are returned in ID order

#### Scenario: Task completion continues to work
- **WHEN** a POST request is made to /api/tasks/{id}/complete for an existing task
- **THEN** the task is marked as completed and returned with HTTP 200
