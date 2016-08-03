Feature: Delete Epics
  As a user
  i want to delete epics of my project
  because i need to make it easier to plan and track progress of large features at a high level

  Background: Create a project
    Given I send a POST request to /projects

      | name   | project for delete epics |
      | public | true                     |

    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/epics

      | name | epic to delete |
    And stored as Epic1

  @deleteProject
  Scenario: delete a  epic
    Given I have the /projects/[Project1.id]/epics/[Epic1.id] endpoint
    When I send a DELETE request
    Then I expect the status code 204
