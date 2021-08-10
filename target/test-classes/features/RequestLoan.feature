Feature: Request Loan

  @RequestLoan
  Scenario: User is able to make request for loan
    Given User is logged in
    And User clicks on request loan link
    When user enters loan amount as "200" and Down payment as "10"
    And selects account number and clicks on apply button
    Then loan request should be successful and success message should be given