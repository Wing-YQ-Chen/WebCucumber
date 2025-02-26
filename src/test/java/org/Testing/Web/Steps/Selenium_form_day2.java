package org.Testing.Web.Steps;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.Testing.Web.Pages.SeleniumFromPage;
import org.openqa.selenium.TakesScreenshot;

public class Selenium_form_day2 {

    WebDriver driver;
    SeleniumFromPage sfPage;
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(options);
        this.driver.manage().window().maximize();
        this.scenario = scenario;
    }

    @After
    public void afterScenario() {
        this.sfPage.driver.quit();
    }

    @AfterStep
    public void afterStep() {
        TakesScreenshot ts = (TakesScreenshot) this.sfPage.driver;
        this.scenario.attach(ts.getScreenshotAs(org.openqa.selenium.OutputType.BYTES), "image/png", "Step Screenshot");
    }

    @Given("i am on the selenium form page")
    public void i_am_on_the_selenium_form_page() {
        this.driver.get("https://seleniumbase.io/demo_page/");
        this.sfPage = new SeleniumFromPage(driver);
    }

    @When("i fill in the inputbox with {string}")
    public void i_fill_in_the_inputbox_with(String string) {
        this.sfPage.TextareaInputBox.sendKeys(string);
    }

    @Then("i should to see the inputbox is filled {string}")
    public void i_should_to_see_the_inputbox_is_filled(String string) {
        assert this.sfPage.TextareaInputBox.getAttribute("value").equals(string);
    }

    @When("i fill in the Read-Only inputbox with {string}")
    public void i_fill_in_the_read_only_inputbox_with(String string) {
        this.sfPage.readOnlyInputBox.sendKeys(string);
    }

    @Then("i should to see the Read-Only inputbox is changed nothing")
    public void i_should_to_see_the_read_only_inputbox_is_changed_nothing() {
        assert this.sfPage.readOnlyInputBox.getAttribute("value").contains("The Color");
    }


}


