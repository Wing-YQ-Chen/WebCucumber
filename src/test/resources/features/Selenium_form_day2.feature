Feature: Selenium_form_day2

  Background: user is on the selenium form page
    Given i am on the selenium form page

  Scenario: user submits selenium form successfully
    When i fill in the inputbox with "test"
    Then i should to see the inputbox is filled "test"

  Scenario: user submits selenium form fail
    When i fill in the Read-Only inputbox with "test"
    Then i should to see the Read-Only inputbox is changed nothing


