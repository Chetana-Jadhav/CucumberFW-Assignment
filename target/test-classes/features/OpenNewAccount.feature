Feature: Open New Account

Scenario: User open new account
  Given User is logged in
  And User clicks on open new account link
  Then  user is navigated to Open account page
  When User select account type as "SAVINGS" and default account number
  And User clicks on Open New Account button
  Then Account is generated and message is given with account number link
