Feature: Manage a pet in the store
  As a user of the Pet store API
  I want to create, fetch, and delete a pet
  So that I can manage the pet's information in the store

  Background:
    Given The base URL for the API is set

  Scenario: Successfully create a new pet
    Given There is a new pet to add with id 10
    When I submit the pet creation
    Then I should receive a successful response
    And the response should include the correct pet information

  Scenario: Successfully consult pet details
    Given I have a pet with id 10
    When I ask for the pet details
    Then I should receive a confirmation that the request was successful
    And I should see the pet's id in the response

  Scenario: Successfully delete a pet
    Given I have a pet with id 10 that I want to delete
    When I delete the pet
    Then I should receive a confirmation that the pet was deleted
    And the pet should no longer exist in the store