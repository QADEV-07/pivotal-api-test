Feature: Get Epic
  As a user
  I want to get  information of the epics
  Because i need to know the values the epic info

  Background: Create a project
    Given I send a POST request to /projects

      | name   | project for get epics |
      | public | true                  |

    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/epics

      | name | Post example hs11h |
    And stored as Epic1

  @deleteProject
  Scenario: Get all epics
    Given I send a GET request to /projects/[Project1.id]/epics endpoint
    Then I expect the status code 200


#  //@smoke_test @epic
  @deleteProject
  Scenario: Get epic by id
    Given I send a GET request to /projects/[Project1.id]/epics/[Epic1.id] endpoint
    Then I expect the status code 200
