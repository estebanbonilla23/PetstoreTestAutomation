Feature: Manage a pet in the store
  As a user of the Pet store API
  I want to create, fetch, and delete a pet
  So that I can manage the pet's information in the store

  Scenario: Successfully create a new pet
    Given There is a new pet to add with id 10
    When I submit the pet creation
    Then I should receive a successful response
    And the response should include the correct pet information

  Scenario: Successfully fetch pet details by ID
    Given I have a pet with id 10
    When I ask for the pet details
    Then I should receive a confirmation that the request was successful
    And I should see the pet's ID in the response

  Scenario: Successfully delete a pet by ID
    Given I have a pet with id 10 that I want to delete
    When I delete the pet
    Then I should receive a confirmation that the pet was deleted
    And the pet should no longer exist in the store