Feature: Transfer Funds

  @Transfer_Funds
  Scenario: User is able to transfer funds
    Given User is logged in
    And User clicks on Transfer Funds link
    Then User is navigated to "ParaBank | Transfer Funds" page
    When User enters amount and  select sender account number and recipient account number
    And User clicks on TRANSFER button
    Then Transfer Complete message is  displayed