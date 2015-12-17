Feature: ExhibitionSection List Page
    In order to manage ExhibitionSections
    As a User
    I need a Page detailing the Current ExhibitionSections

Background:
    Given I have an Empty Database

Scenario: There are No Exhibitions
    Given I am on "/exhibitionsection/"
     Then I should see a "table#exhibitionsection-list" element
      And I should see "No Default Show is set." in the "#exhibitionsection-list" element

Scenario: There is No Default Exhibition
    Given I have an Exhibition "Spring Show 2011"
      And I save everything
     When I am on "/exhibitionsection/"
     Then I should see a "table#exhibitionsection-list" element
      And I should see "No Default Show is set." in the "#exhibitionsection-list" element

Scenario: There are No ExhibitionSections#
    Given I have an Exhibition "Spring Show 2011"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything
     When I am on "/exhibitionsection/"
     Then I should see a "table#exhibitionsection-list" element
      And I should see "No Show Sections Exist." in the "#exhibitionsection-list" element

Scenario: There is One ExhibitionSection
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything
     When I am on "/exhibitionsection/"
     Then I should see a "table#exhibitionsection-list" element
      And I should see "Vegtables" in the "#exhibitionsection-list" element

Scenario: There are Many ExhibitionSections
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything
     When I am on "/exhibitionsection/"
     Then I should see a "table#exhibitionsection-list" element
      And I should see "Vegtables" in the "#exhibitionsection-list" element
      And I should see "Flowers" in the "#exhibitionsection-list" element

Scenario: Buttons exist to View, Edit and Delete an ExhibitionSection
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything
     When I am on "/exhibitionsection/"
     Then I should see an "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'1'" element
      And I should see an "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
      And I should see an "ExhibitionSection delete button for 'Spring Show 2011':'1'" element
