@tbc
Feature: Edit ExhibitionSection
    In order to manage ExhibitionSections
    As a User
    I need to be able to rename an ExhibitionSection

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I Make Exhibition "Spring Show 2011" Default
      And I Describe ExhibitionSection "Spring Show 2011':'2" as "Description"  
      And I am on "/exhibitionsection/" 

Scenario: ExhibitionSection Edit Button Exists
    Given I should see a "table#exhibitionsection-list" element
      And I should see "Vegtables" in the "#exhibitionsection-list" element
      And I should see a "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
      And I should see "Flowers" in the "#exhibitionsection-list" element
      And I should see a "ExhibitionSection edit button for 'Spring Show 2011':'2'" element
    
Scenario: Edit a ExhibitionSection
    Given I am on "/exhibitionsection/"
     Then I should see a "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
     When I follow "ExhibitionSection edit link for 'Spring Show 2011':'1'"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'1'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_name" field should contain "1"
     When I fill in "ons_showmanagerbundle_exhibitionsection_name" with "1A"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'1A'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_name" field should contain "1A"
     When I follow "back"
     Then I should be on "/exhibitionsection/"
      And I should see a "table#exhibitionsection-list" element
      And I should see "1A" in the "#exhibitionsection-list" element

Scenario: Cannot Edit a ExhibitionSection to have the same number 
    Given I am on "/exhibitionsection/"
     Then I should see a "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
     When I follow "ExhibitionSection edit link for 'Spring Show 2011':'1'"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'1'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_name" field should contain "1"
     When I fill in "ons_showmanagerbundle_exhibitionsection_name" with "2"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'1'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_name" field should contain "2"
      And I should see "This value is already used." in the "#ons_showmanagerbundle_exhibitionsection" element

Scenario: Edit a ExhibitionSection adding a Description
    Given I am on "/exhibitionsection/"
     Then I should see a "ExhibitionSection edit button for 'Spring Show 2011':'1'" element
     When I follow "ExhibitionSection edit link for 'Spring Show 2011':'1'"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'1'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_description" field should contain ""
     When I fill in "ons_showmanagerbundle_exhibitionsection_description" with "now described"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'1'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_description" field should contain "now described"
     When I follow "back"
     Then I should be on "/exhibitionsection/"
      And I should see a "table#exhibitionsection-list" element
      And I should see "(now described)" in the "#exhibitionsection-list" element

Scenario: Edit a ExhibitionSection removing a Description
    Given I am on "/exhibitionsection/"
     Then I should see a "ExhibitionSection edit button for 'Spring Show 2011':'2'" element
     When I follow "ExhibitionSection edit link for 'Spring Show 2011':'2'"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'2'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_description" field should contain "description"
     When I fill in "ons_showmanagerbundle_exhibitionsection_description" with ""
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection edit page for 'Spring Show 2011':'2'"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And the "ons_showmanagerbundle_exhibitionsection_description" field should contain ""
     When I follow "back"
     Then I should be on "/exhibitionsection/"
      And I should see a "table#exhibitionsection-list" element
      And I should not see "(description)" in the "#exhibitionsection-list" element
