@tbc
Feature: Create an ExhibitionSection with a New Name
    In order to manage exhibitionsections
    As a User
    I need to be able to create an exhibitionsection

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2010" 
      And I have an Exhibition "Spring Show 2011" 
      And I have a Section "Vegtables"
      And I have a Section "Flowers"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything                                                    

Scenario: New ExhibitionSection Button Exists
    Given I am on "/exhibitionsection/" 
     Then I should see a "a#new_exhibitionsection" element

Scenario: New ExhibitionSection Form Exists
    Given I am on "/exhibitionsection/new" 
     Then I should see a "div#ons_showmanagerbundle_exhibitionsection" element

Scenario: An ExhibitionSection needs a Section
    Given I am on "/exhibitionsection/new" 
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
     When I fill in "Name" with "1"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "/exhibitionsection/" 
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element

Scenario: An ExhibitionSection needs a Section
    Given I am on "/exhibitionsection/new" 
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
     When I select "Vegtables" from "ons_showmanagerbundle_exhibitionsection_section"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "/exhibitionsection/" 
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element

Scenario: Create a ExhibitionSection
    Given I am on "/exhibitionsection/new" 
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
     When I fill in "Name" with "1"
      And I select "Vegtables" from "ons_showmanagerbundle_exhibitionsection_section"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'1'"
      And I should see a "table#exhibitionsection" element
      And I should see "Vegtables" in the "#exhibitionsection" element
     When I follow "back"
     Then I should be on "/exhibitionsection/"

Scenario: Create a Second ExhibitionSection for the same Section
    Given I link Exhibition "Spring Show 2011" to Section "Vegtables" as Section Exhibition Named "1" 
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
     When I am on "/exhibitionsection/new" 
      And I fill in "Name" with "2"
      And I select "Vegtables" from "ons_showmanagerbundle_exhibitionsection_section"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'2'"
      And I should see a "table#exhibitionsection" element
      And I should see "Vegtables" in the "#exhibitionsection" element
     When I follow "back"
     Then I should be on "/exhibitionsection/"

Scenario: Cannot Create a Second ExhibitionSection for the same Section Number
    Given I link Exhibition "Spring Show 2011" to Section "Vegtables" as Section Exhibition Named "1" 
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
     When I am on "/exhibitionsection/new" 
      And I fill in "Name" with "1"
      And I select "Vegtables" from "ons_showmanagerbundle_exhibitionsection_section"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "/exhibitionsection/"
      And I should see a "div#ons_showmanagerbundle_exhibitionsection" element
      And I should see "This value is already used." in the "#ons_showmanagerbundle_exhibitionsection" element

Scenario: Create a ExhibitionSection with a Description
    Given I am on "/exhibitionsection/new" 
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
     When I fill in "Name" with "1"
      And I fill in "ons_showmanagerbundle_exhibitionsection_description" with "new description"
      And I select "Vegtables" from "ons_showmanagerbundle_exhibitionsection_section"
      And I press "ons_showmanagerbundle_exhibitionsection_submit"
     Then I should be on "ExhibitionSection exhibitionsection page for 'Spring Show 2011':'1'"
      And I should see a "table#exhibitionsection" element
      And I should see "Vegtables" in the "#exhibitionsection" element
      And I should see "(new description)" in the "#exhibitionsection" element
     When I follow "back"
     Then I should be on "/exhibitionsection/"
