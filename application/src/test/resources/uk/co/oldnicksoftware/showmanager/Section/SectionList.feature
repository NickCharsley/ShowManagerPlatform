@tbc
Feature: Section List Page
    In order to manage Sections
    As a User
    I need a Page detailing the Current Sections

Background:
    Given I have an Empty Database

Scenario: There are No Sections
    Given I am on "/section/"
     Then I should see a "table#section-list" element
      And I should see "No Sections have been found." in the "#section-list" element

Scenario: There is One Section
    Given I have a Section "Vegtables"
      And I save everything
     When I am on "/section/"
     Then I should see a "table#section-list" element
      And I should see "Vegtables" in the "#section-list" element

Scenario: There are Many Sections
    Given I have a Section "Vegtables"
      And I have a Section "Flowers"
      And I save everything
     When I am on "/section/"
     Then I should see a "table#section-list" element
      And I should see "Vegtables" in the "#section-list" element
      And I should see "Flowers" in the "#section-list" element

Scenario: Buttons exist to View, Edit and Delete a Section
    Given I have a Section "Vegtables"
      And I save everything
     When I am on "/section/"
     Then I should see a "Section section button for Vegtables" element
      And I should see a "Section edit button for Vegtables" element
      And I should see a "Section delete button for Vegtables" element
