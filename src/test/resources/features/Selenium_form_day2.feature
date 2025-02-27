Feature: Selenium_form_day2

  Background: user is on the selenium form page
    Given i am on the selenium form page

  Scenario Outline: user submits selenium form successfully
    When i fill in the inputbox with "<text>"
    Then i should to see the inputbox is filled "<text>"
    Examples:
      | text   |
      | test@1 |
      | test@2 |
      | test@3 |

  Scenario: user submits selenium form fail
    When i fill in the Read-Only inputbox with following text
      | test@1 |
      | test@2 |
      | test@3 |
    Then i should to see the Read-Only inputbox is changed nothing



