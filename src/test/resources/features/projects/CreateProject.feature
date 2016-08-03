Feature: Testing for login Pivotal page

  @smoke @deleteAllProject
  Scenario: Create project
    Given I send a POST request to /projects
      | name   | TestCreateProjects |
      | public | true               |
    Then I expect the status code 200