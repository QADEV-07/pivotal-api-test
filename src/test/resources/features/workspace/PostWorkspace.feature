@CleanEnviroment
Feature: delete Workspace

  As a user
  I want to create a new workspace with valid data

  @DeleteWorkspace
  Scenario: Create Workspaces
    Given I send a POST request to /my/workspaces
      | name | workspaceTest |
    Then I expect the status code 200
    And The name field should be equal to workspaceTest
