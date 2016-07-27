Feature:Delete project

  Background: Create a project
    Given I send a POST request to /projects

      | name   | project to delete |
      | public | true              |

    And stored as Project1

  Scenario: Delete project
    Given I have the /projects/[Project1.id] endpoint
    When I send a DELETE request
    Then I expect the status code 204
