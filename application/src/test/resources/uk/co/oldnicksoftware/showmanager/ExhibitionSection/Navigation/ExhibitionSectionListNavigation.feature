@tbc
Feature: ExhibitionSection List Page
    In order to manage ExhibitionSections
    As a User
    I need a Page detailing the Current ExhibitionSections

Background:
    Given I have an Empty Database

Scenario: Buttons exist to View, Edit and Delete an ExhibitionSection
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsection/"
     Then I should see an "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'1'" element
      And I should see an "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
      And I should see an "ExhibitionSection delete button for 'Spring Show 2011':'1'" element
