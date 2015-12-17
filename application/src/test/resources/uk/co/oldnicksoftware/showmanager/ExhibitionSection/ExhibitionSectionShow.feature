Feature: Show ExhibitionSection
    In order to manage ExhibitionSections
    As a User
    I need to be able to view an ExhibitionSection

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I Make Exhibition "Spring Show 2011" Default
      And I Describe ExhibitionSection "Spring Show 2011':'2" as "Description"  
      And I save everything

Scenario: ExhibitionSection exhibitionsection Button Exists
    Given I am on "/exhibitionsection/" 
     Then I should see a "table#exhibitionsection-list" element
      And I should see "Vegtables" in the "#exhibitionsection-list" element
      And I should see a "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'1'" element
      And I should see "Flowers" in the "#exhibitionsection-list" element
      And I should see a "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'2'" element
    
Scenario: Show an ExhibitionSection with no Description
    Given I am on "/exhibitionsection/" 
     Then I should see a "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'1'" element
     When I follow "ExhibitionSection exhibitionsection link for 'Spring Show 2011':'1'"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'1'"
      And I should see a "table#exhibitionsection" element
      And I should see "Vegtables" in the "#exhibitionsection" element
      And I should not see "(Description)" in the "#exhibitionsection" element
     When I follow "back"
     Then I should be on "/exhibitionsection/"

Scenario: Show an ExhibitionSection with a Description
    Given I am on "/exhibitionsection/" 
     Then I should see a "ExhibitionSection exhibitionsection button for 'Spring Show 2011':'2'" element
     When I follow "ExhibitionSection exhibitionsection link for 'Spring Show 2011':'2'"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'2'"
      And I should see a "table#exhibitionsection" element
      And I should see "Flowers" in the "#exhibitionsection" element
      And I should see "(Description)" in the "#exhibitionsection" element
     When I follow "back"
     Then I should be on "/exhibitionsection/"

Scenario: If we have no Default Exhibition we bounce back to Show Page
    Given I Remove Exhibition "Spring Show 2011" Default    
      And I save everything                                                    
      And I am on "/exhibitionsection/None"
     Then I should be on "/show/"
