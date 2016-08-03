@CleanEnvironment
Feature: Create Workspace

  As a user
  I want to get all workspaces created with a request

  Background: Create a workspace
    Given I send a POST request to /my/workspaces
      | name | WorkspaceTest1 |
    And stored as Workspace1

    Given I send a POST request to /my/workspaces
      | name | WorkspaceTest2 |
    And stored as Workspace2

  @DeleteWorkspaces
  Scenario: Get All Workspaces
    Given I send a GET request to /my/workspaces endpoint
    Then I expect the status code 200
