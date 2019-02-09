Feature: test feature


  Scenario: open google and search
    Given I am on the google homepage
    When I search for "selenide"
    Then I see results