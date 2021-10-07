Feature: feature to test login

  Scenario: Check login test

    Given user is on login page
    When user enters username and password
    And click login button
    Then user is login


