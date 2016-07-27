Feature:Update project

  Background: set connection to pivotal tracker API REST
    Given I sent a POST request to /projects
      | name   | project to update |
      | public | true              |

    And stored as Project1

  @project
  Scenario: Update name of project
    Given I send a PUT request to /projects/[Project1.id]

      | name   | project updated |

    Then I expect the status code 200
    And The project name should be equal to project updated
