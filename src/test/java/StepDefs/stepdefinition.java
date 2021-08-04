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
}
