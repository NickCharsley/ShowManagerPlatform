Feature: Create an Section with a New Name
    In order to manage sections
    As a User
    I need to be able to create an section

Background:
    Given I have an Empty Database 

Scenario: New Section Button Exists
    Given I am on "/section/" 
     Then I should see a "a#new_section" element

Scenario: New Section Form Exists
    Given I am on "/section/new" 
     Then I should see a "div#ons_showmanagerbundle_section" element

Scenario: Create a Section
    Given I am on "/section/new" 
     Then I have 0 Records in the "ONSShowManagerBundle Section" repository
     When I fill in "Name" with "New Section"
      And I press "ons_showmanagerbundle_section_submit"
     Then I should be on "Section section page for New Section"
      And I should see a "table#section" element
      And I should see "New Section" in the "#section" element
     When I follow "back"
     Then I should be on "/section/"

Scenario: Section Names are Unique
    Given I have a Section "Spring Section 2011"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I am on "/section/new" 
      And I fill in "Name" with "Spring Section 2011"
      And I press "ons_showmanagerbundle_section_submit"
     Then I should be on "/section/"
      And I should see a "div#ons_showmanagerbundle_section" element
      And I should see "This value is already used." in the "#ons_showmanagerbundle_section" element
