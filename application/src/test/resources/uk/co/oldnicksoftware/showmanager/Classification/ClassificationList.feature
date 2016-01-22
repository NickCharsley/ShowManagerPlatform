@tbc
Feature: Classification List Page
    In order to manage Classifications
    As a User
    I need a Page detailing the Current Classifications

Background:
    Given I have an Empty Database

Scenario: There are No Classifications
    Given I am on "/classification/"
     Then I should see a "table#classification-list" element
      And I should see "No Classifications have been found." in the "#classification-list" element

Scenario: There is One Classification
    Given I have a Classification "Beetroot"
     When I am on "/classification/"
     Then I should see a "table#classification-list" element
      And I should see "Beetroot" in the "#classification-list" element

Scenario: There are Many Classifications
    Given I have a Classification "Beetroot"
      And I have a Classification "Onions"
     When I am on "/classification/"
     Then I should see a "table#classification-list" element
      And I should see "Beetroot" in the "#classification-list" element
      And I should see "Onions" in the "#classification-list" element

Scenario: Buttons exist to View, Edit and Delete a Classification
    Given I have a Classification "Beetroot"
     When I am on "/classification/"
     Then I should see a "Classification classification button for Beetroot" element
      And I should see a "Classification edit button for Beetroot" element
      And I should see a "Classification delete button for Beetroot" element
