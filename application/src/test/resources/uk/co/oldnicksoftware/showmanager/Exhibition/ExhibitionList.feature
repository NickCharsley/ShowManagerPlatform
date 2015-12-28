@tbc
Feature: Exhibition List Page
    In order to manage Exhibitions
    As a User
    I need a Page detailing the Current Exhibitions

Background:
    Given I have an Empty Database

Scenario: There are No Exhibitions
    Given I am on "/show/"
     Then I should see a "table#show-list" element
      And I should see "No Shows have been found." in the "#show-list" element

Scenario: There is One Exhibition
    Given I have an Exhibition "Spring Show 2011"
      And I save everything
     When I am on "/show/"
     Then I should see a "table#show-list" element
      And I should see "Spring Show 2011" in the "#show-list" element

Scenario: There are Many Exhibitions
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I save everything
     When I am on "/show/"
     Then I should see a "table#show-list" element
      And I should see "Spring Show 2011" in the "#show-list" element
      And I should see "Spring Show 2012" in the "#show-list" element
