package org.Testing.Web.Steps;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.Testing.Web.Pages.SeleniumFromPage;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.datatable.DataTable;

import java.util.Date;

public class Selenium_form_day2 {

    WebDriver driver;
    SeleniumFromPage sfPage;
    private Scenario scenario;
    long startTime;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.startTime = System.currentTimeMillis();
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(options);
        this.driver.manage().window().maximize();
        this.scenario = scenario;
    }

    @After
    public void afterScenario() {
        this.sfPage.driver.quit();

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;
        this.scenario.log("Started at " + new Date(this.startTime));
        this.scenario.log("Ended at " + new Date(endTime));
        this.scenario.log("Duration: " + duration + " seconds");
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
        // 添加步骤描述
        this.scenario.attach("Step Description: I am on the selenium form page", "text/plain", "Step Description");
    }

    @When("i fill in the inputbox with {string}")
    public void i_fill_in_the_inputbox_following_text(String string) {
        this.sfPage.TextareaInputBox.sendKeys(string);
    }


    @Then("i should to see the inputbox is filled {string}")
    public void i_should_to_see_the_inputbox_is_filled(String string) {
        String value = this.sfPage.TextareaInputBox.getAttribute("value");
        assert value != null && value.equals(string);
    }


    @When("i fill in the Read-Only inputbox with following text")
    public void i_fill_in_the_read_only_inputbox_following_text(DataTable dataTable) {
        dataTable.asList().forEach(string -> {
            this.sfPage.readOnlyInputBox.sendKeys(string);
        });
    }


    @Then("i should to see the Read-Only inputbox is changed nothing")
    public void i_should_to_see_the_read_only_inputbox_is_changed_nothing() {
        String value = this.sfPage.readOnlyInputBox.getAttribute("value");
        assert value != null && value.contains("The Color");
    }

}


