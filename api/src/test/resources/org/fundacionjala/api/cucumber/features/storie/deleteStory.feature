Feature: Delete Story

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
  Scenario: Delete a story
    Given I have the /projects/[Project1.id]/stories/[Story1.id] endpoint
    When I send a DELETE request
    Then I expect the status code 204