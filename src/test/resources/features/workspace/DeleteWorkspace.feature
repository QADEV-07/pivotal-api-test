@CleanEnviroment
Feature: Delete Workspace

  As a user
  I want to delete a workspace previously created
  so that I expect that workspace selected is deleted

  Background: Create a workspace
    Given I send a POST request to /my/workspaces
      | name | WorkspaceTest1 |
    And stored as Workspace1

  Scenario: delete a workspace without projects
    Given I have the /my/workspaces/[Workspace1.id] endpoint
    When I send a DELETE request
    Then I expect the status code 204
