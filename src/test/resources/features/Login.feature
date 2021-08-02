Feature: login feature

  Scenario: User is able to login in the application
    Given User opened browser
    And user navigated to the application URL
    When User entered username as "john" and password as "demo" and click on login button
    Then User is able to login in the application