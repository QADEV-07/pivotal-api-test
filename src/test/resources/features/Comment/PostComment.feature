Feature: Post comments into a story

  Background: Connection
    Given I send a POST request to /projects
      | name   | ProjectTest |
      | public | true        |
    And stored as Project1
    Then I expect the status code 200
    And I send a POST request to /projects/[Project1.id]/stories
      | name | StoryTest |
    And stored as Story1
    Then I expect the status code 200

  @deleteProject
  Scenario: Create a comment
    When I send a POST request to /projects/[Project1.id]/stories/[Story1.id]/comments
      | text | CommentTest |
    And stored as Comment1
    Then I expect the status code 200
    And I expect that [Comment1.text] be CommentTest
    And I expect that [Comment1.kind] be comment

  Scenario: Create a comment
    When I send a POST request to /projects/[Project1.id]/stories/[Story1.id]/comments
      | text | CommentTest |
    And stored as Comment1
    Then I expect the status code 200
    And I expect that [Comment1.text] not be CommentTest12
    And I expect that [Comment1.kind] not be story