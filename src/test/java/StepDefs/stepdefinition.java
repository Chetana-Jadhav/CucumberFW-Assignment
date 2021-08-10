package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class stepdefinition {

   WebDriver driver ;
   String URL = "https://parabank.parasoft.com/parabank/index.htm";
   String userName = "john";
   String password = "demo";
   String acctype = "SAVINGS";
   Scenario scenario;

   @Before  //native dependency injection in cucumber
   public void setUp(Scenario scenario){
        this.scenario = scenario;
   }
   @After
   public void cleanUp(){
      // driver.quit();
   }
    @Given("User opened browser")
    public void user_opened_browser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Given("user navigated to the application URL")
    public void user_navigated_to_the_application_url() {
       driver.get(URL);

    }
    @When("User entered username as {string} and password as {string} and click on login button")
    public void user_entered_username_as_and_password_as_and_click_on_login_button(String userName, String password) {
    driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys(userName);
    driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(password);
    driver.findElement(By.xpath("//input[@class= 'button']")).click();

    }
    @Then("User is able to login in the application")
    public void user_is_able_to_login_in_the_application() {
     Assert.assertEquals("ParaBank | Accounts Overview", driver.getTitle());
     boolean displayedTable =  driver.findElement(By.xpath("//table[@id='accountTable']")).isDisplayed();
       Assert.assertEquals(true,displayedTable);

    }
    @Given("User is logged in")
    public void user_is_logged_in() {
        user_opened_browser();
        user_navigated_to_the_application_url();
        user_entered_username_as_and_password_as_and_click_on_login_button( userName,  password);

    }
    @Given("User clicks on open new account link")
    public void user_clicks_on_open_new_account_link() {
        driver.findElement(By.partialLinkText("Open New Account")).click();

    }
    @Then("user is navigated to Open account page")
    public void user_is_navigated_to_open_account_page() {
        Assert.assertEquals(driver.getTitle(),"ParaBank | Open Account");

    }
    @When("User select account type as {string} and default account number")
    public void user_select_account_type_as_and_default_account_number( String acctype) {
        Select accType = new Select(driver.findElement(By.id("type")));
        accType.selectByVisibleText(acctype);
        Select accNumber = new Select(driver.findElement(By.id("fromAccountId")));
        accNumber.selectByIndex(0);
    }
    @When("User clicks on Open New Account button")
    public void user_clicks_on_open_new_account_button() throws InterruptedException {
        Thread.sleep(5000);
      driver.findElement(By.xpath("//input[@value='Open New Account']")).click();
    }
    @Then("Account is generated and message is given with account number link")
    public void account_is_generated_and_message_is_given_with_account_number_link() {
       WebElement element = driver.findElement(By.id("newAccountId"));
       String accNumber = element.getText();
       Assert.assertEquals(element.isDisplayed(),true);
       scenario.log("New generated account number is : " + accNumber);
    }

    @Given("User clicks on Transfer Funds link")
    public void user_clicks_on_transfer_funds_link() {
       driver.findElement(By.xpath("//a[text()='Transfer Funds']")).click();

    }
    @Then("User is navigated to {string} page")
    public void user_is_navigated_to_page(String string) {
       Assert.assertEquals(driver.getTitle(),string);
    }


    @When("User enters amount and  select sender account number and recipient account number")
    public void user_enter_amount_and_select_sender_account_number_and_recipient_account_number() throws InterruptedException {
       driver.navigate().refresh();
       Thread.sleep(2000);
       driver.findElement(By.id("amount")).sendKeys("2");
       Thread.sleep(2000);
       Select FromAcc = new Select(driver.findElement(By.id("fromAccountId")));
       FromAcc.selectByIndex(0);
       Thread.sleep(2000);
       Select ToAcc = new Select(driver.findElement(By.id("toAccountId")));
       ToAcc.selectByIndex(1);

    }
    @When("User clicks on TRANSFER button")
    public void user_clicks_on_transfer_button() {
      driver.findElement(By.xpath("//input[@value='Transfer']")).click();
    }
    @Then("Transfer Complete message is  displayed")
    public void transfer_complete_message_is_displayed() {
       WebElement SuccessMsg = driver.findElement(By.xpath("//h1[text()='Transfer Complete!']"));
       Assert.assertEquals(SuccessMsg.isDisplayed(),true);
       WebElement amount = driver.findElement(By.id("amount"));
       WebElement FromAccNo = driver.findElement(By.id("fromAccountId"));
       WebElement ToAccNo = driver.findElement(By.id("toAccountId"));
        String amountText = amount.getText();
        String fromAcNm = FromAccNo.getText();
        String toAcNm = ToAccNo.getText();
        scenario.log("Message : " + amountText+ "has been transferred from account "+fromAcNm+ "to account "+toAcNm);
    }
    @When("User clicks on Bill Pay link")
    public void user_clicks_on_bill_pay_link() {
        driver.findElement(By.linkText("Bill Pay")).click();
    }
    @Then("user is navigated to ParaBank | Bill Pay page")
    public void user_is_navigated_to_para_bank_bill_pay_page() {
       Assert.assertEquals(driver.getTitle(),"ParaBank | Bill Pay");

    }
    @When("User enter Payees Name as {string} and Address as {string} and City as {string} State as {string} and Zipcode as {string} and")
    public void user_enter_payees_name_as_and_address_as_and_city_as_state_as_and_zipcode_as_and(String userName, String string2, String string3, String string4, String string5) {
        driver.findElement(By.xpath("//input[@name ='payee.name']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name ='payee.address.street']")).sendKeys(string2);
        driver.findElement(By.xpath("//input[@name ='payee.address.city']")).sendKeys(string3);
        driver.findElement(By.xpath("//input[@name ='payee.address.state']")).sendKeys(string4);
        driver.findElement(By.xpath("//input[@name ='payee.address.zipCode']")).sendKeys(string5);
        driver.findElement(By.xpath("//input[@name ='payee.phoneNumber']")).sendKeys("112233");
    }
    @When("User enters Account number as {string} verify account {string} and amount as {string} and selects sender's account number")
    public void user_enters_account_number_as_verify_account_and_amount_as_and_selects_sender_s_account_number(String string, String string2, String string3) {
        driver.findElement(By.xpath("//input[@name ='payee.accountNumber']")).sendKeys(string);
        driver.findElement(By.xpath("//input[@name ='verifyAccount']")).sendKeys(string2);
        driver.findElement(By.xpath("//input[@name ='amount']")).sendKeys(string3);
        Select AcNum = new Select(driver.findElement(By.name("fromAccountId")));
        AcNum.selectByIndex(0);
    }
    @When("clicks on SEND PAYMENTS button")
    public void clicks_on_send_payments_button() {
        driver.findElement(By.xpath("//input[@value = 'Send Payment']")).click();
    }
    @Then("Bill payment is completed and success Message is given")
    public void bill_payment_is_completed_and_success_message_is_given() throws InterruptedException {
       WebElement SuccessMsg = driver.findElement(By.xpath("//h1[@class ='title' and text() = 'Bill Payment Complete']"));
       String SM = SuccessMsg.getText();
       //Assert.assertEquals(SuccessMsg.isDisplayed(), true, "Success message is displayed");
      WebElement payeename = driver.findElement(By.xpath("//span[@id='payeeName']"));
      WebElement Amount = driver.findElement(By.id("amount"));
      Thread.sleep(2000);
      WebElement senderAcc = driver.findElement(By.id("fromAccountId"));
      Thread.sleep(2000);
      String senderAccount = senderAcc.getText();
      Thread.sleep(2000);
      String amt = Amount.getText();
      Thread.sleep(2000);
      String PayeeName = payeename.getText();
      Thread.sleep(2000);
      scenario.log("Bill Payment to "+ PayeeName + " in the amount of "+ amt + " from account "+ senderAccount + " was successful.");
    }
    @Given("User clicks on request loan link")
    public void user_clicks_on_request_loan_link() {
       driver.findElement(By.partialLinkText("Request Loan")).click();
    }
    @When("user enters loan amount as {string} and Down payment as {string}")
    public void user_enters_loan_amount_as_and_down_payment_as(String string, String string2) {
        driver.findElement(By.id("amount")).sendKeys(string);
        driver.findElement(By.id("downPayment")).sendKeys(string2);

    }
    @When("selects account number and clicks on apply button")
    public void selects_account_number_and_clicks_on_apply_button() {
        Select FromAccount = new Select(driver.findElement(By.id("fromAccountId")));
        FromAccount.selectByIndex(0);
        driver.findElement(By.xpath("//input[@value= 'Apply Now']")).click();
    }
    @Then("loan request should be successful and success message should be given")
    public void loan_request_should_be_successful_and_success_message_should_be_given() {
       WebElement message = driver.findElement(By.xpath("//h1[text()='Loan Request Processed']"));
       //WebElement congratsmsg = driver.findElement(By.xpath("//p[text()='Congratulations, your loan has been approved.']"));
      // WebElement AccNoMsg = driver.findElement(By.xpath("//b[text()='Your new account number:']"));
       WebElement loanProvider = driver.findElement(By.id("loanProviderName"));
       WebElement date = driver.findElement(By.id("responseDate"));
       WebElement loanStatus = driver.findElement(By.id("loanStatus"));
       //WebElement NewAcNum = driver.findElement(By.id("newAccountId"));
      // String New_Ac_No = NewAcNum.getText();
       String Loan_status = loanStatus.getText();
       String reponseDate = date.getText();
       String Loan_provider = loanProvider.getText();
       //String AcNumMsg = AccNoMsg.getText();
      // String congomsg = congratsmsg.getText();
       String successmsg = message.getText();
      // scenario.log("Message : "+successmsg + congomsg +  AcNumMsg + New_Ac_No );
       scenario.log("Loan Provider : " +Loan_provider + " Date : " +reponseDate+ " Status : " +Loan_status );
       Assert.assertEquals(reponseDate, "10-08-2021");

    }

}
