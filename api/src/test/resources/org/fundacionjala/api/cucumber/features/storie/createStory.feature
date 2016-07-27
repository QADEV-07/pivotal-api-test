Feature: Create Story

  Background: create project
    Given I sent a POST request to /projects
      | name   | project to create stories |
      | public | true                      |
    And stored as Project1

  @story
  Scenario: Create a story
    Given  I send a POST request to /projects/[Project1.id]/stories
      | name        | story example          |
      | description | example of description |
    Then I expect the status code 200
    And The story name should be equal to story example
