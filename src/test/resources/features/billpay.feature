Feature: Billpay Feature

  @Billpay
  Scenario: user is able to make bill payment
    Given User is logged in
    When User clicks on Bill Pay link
    Then user is navigated to ParaBank | Bill Pay page
    When User enter Payees Name as "john" and Address as "Dummy Address" and City as "Pune" State as "Maharashtra" and Zipcode as "123" and
    And User enters Account number as "14010" verify account "14010" and amount as "5" and selects sender's account number
    And clicks on SEND PAYMENTS button
    Then Bill payment is completed and success Message is given
