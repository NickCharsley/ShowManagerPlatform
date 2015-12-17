Feature: Edit Section
    In order to manage Sections
    As a User
    I need to be able to rename an Section

Background:
    Given I have an Empty Database 
      And I have a Section "Vegtables"                            
      And I have a Section "Flowers"   
      And I save everything                                                    
      And I am on "/section/" 

Scenario: Section Edit Button Exists
    Given I should see a "table#section-list" element
      And I should see "Vegtables" in the "#section-list" element
      And I should see a "Section edit button for Vegtables" element
      And I should see "Flowers" in the "#section-list" element
      And I should see a "Section edit button for Flowers" element
    
Scenario: Edit a Section
    Given I am on "/section/"
     Then I should see a "Section edit button for Vegtables" element
     When I follow "Section edit link for Vegtables"
     Then I should be on "Section section edit page for Vegtables"
      And I should see a "div#ons_showmanagerbundle_section" element
      And the "ons_showmanagerbundle_section_name" field should contain "Vegtables"
     When I fill in "ons_showmanagerbundle_section_name" with "Updated Vegtables"
      And I press "ons_showmanagerbundle_section_submit"
     Then I should be on "Section section edit page for Updated Vegtables"
      And I should see a "div#ons_showmanagerbundle_section" element
      And the "ons_showmanagerbundle_section_name" field should contain "Updated Vegtables"
     When I follow "back"
     Then I should be on "/section/"
      And I should see a "table#section-list" element
      And I should see "Updated Vegtables" in the "#section-list" element

Scenario: Cannot Edit a Section to have the same Name as another Section
    Given I am on "/section/"
     Then I should see a "Section edit button for Vegtables" element
     When I follow "Section edit link for Vegtables"
     Then I should be on "Section section edit page for Vegtables"
      And I should see a "div#ons_showmanagerbundle_section" element
      And the "ons_showmanagerbundle_section_name" field should contain "Vegtables"
     When I fill in "ons_showmanagerbundle_section_name" with "Flowers"
      And I press "ons_showmanagerbundle_section_submit"
     Then I should be on "Section section page for Vegtables"
      And I should see a "div#ons_showmanagerbundle_section" element
      And the "ons_showmanagerbundle_section_name" field should contain "Flowers"
      And I should see "This value is already used." in the "#ons_showmanagerbundle_section" element
