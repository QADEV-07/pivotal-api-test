Feature: Update Story

  Background: create a story inside a new project
    Given I sent a POST request to /projects

      | name   | project to create stories |
      | public | true                      |
    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/stories

      | name        | story example          |
      | description | example of description |
    And stored as Story1

  @story
  Scenario: Update a story
    Given I send a PUT request to /projects/[Project1.id]/stories/[Story1.id]
      | name        | story example  updated         |
      | description | example of description updated |
    Then I expect the status code 200
    And The story name should be equal to story example  updated