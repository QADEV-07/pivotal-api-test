Feature: Post Epic
  As a user
  i want to add new epics to my project
  because i need to make it easier to plan and track progress of large features at a high level

  Background: Create a project
    Given I send a POST request to /projects

      | name   | project for create epics |
      | public | true                     |

    And stored as Project1

  @deleteProject
  Scenario: create a new epic
    Given I send a POST request to /projects/[Project1.id]/epics

      | name | Post example hs11h |
    Then I expect the status code 200
