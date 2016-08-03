Feature: Update Epic
  As a user
  I want to update information of  epics
  Because i need fix some mistakes or miss spells on field values

  Background: Create a project
    Given I send a POST request to /projects

      | name   | project for update epics |
      | public | true                     |

    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/epics

      | name | epic example hs11h |
    And stored as Epic1

  @deleteProject
  Scenario: Update epic
    Given I send a PUT request to /projects/[Project1.id]/epics/[Epic1.id]

      | name | epic example updated |

    Then I expect the status code 200
    And The name field should be equal to epic example updated
