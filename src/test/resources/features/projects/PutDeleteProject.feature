@CleanEnvironment
Feature: Testing for delete project in Pivotal

  Background: create a Project
    Given I send a POST request to /projects
      | name   | Test Edit Project |
      | public | true              |
    And I expect the status code 200
    Then stored as Project1

  @smoke @deleteAllProject
  Scenario: Edit Project
    When I send a PUT request to /projects/[Project1.id]
      | description | totally new |
    Then I expect the status code 200
    And The description field should be equals to totally new

  @smoke
  Scenario: Delete projects
    When I send a DELETE request /projects/[Project1.id]
    Then I expect the status code 204