Feature: Get, Put and Delete Comment

  Background: Connection
    Given I send a POST request to /projects
      | name   | ProjectTest |
      | public | true        |
    And stored as Project1
    And I send a POST request to /projects/[Project1.id]/stories
      | name | StoryTest |
    And stored as Story1
    And  I send a POST request to /projects/[Project1.id]/stories/[Story1.id]/comments
      | text | CommentTestToBeChecked |
    And stored as Comment1

  @deleteProject
  Scenario: Getting a comment
    Given I send a GET request to /projects/[Project1.id]/stories/[Story1.id]/comments/[Comment1.id] endpoint
    Then I expect the status code 200
    And I expect that [Comment1.text] be CommentTestToBeChecked
    And I expect that [Comment1.kind] be comment

  @deleteProject
  Scenario: Deleting a comment
    Given I have the /projects/[Project1.id]/stories/[Story1.id]/comments/[Comment1.id] endpoint
    And I send a DELETE request
    Then I expect the status code 204

  @deleteProject
  Scenario: Update comment
    Given I send a PUT request to /projects/[Project1.id]/stories/[Story1.id]/comments/[Comment1.id]
      | text | CommentTest Updated |
    Then I expect the status code 200
    And The text field should be equal to CommentTest Updated
    And I expect that [Comment1.kind] be comment