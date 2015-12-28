@tbc
Feature: Show Section
    In order to manage Sections
    As a User
    I need to be able to view an Section

Background:
    Given I have an Empty Database 
      And I have a Section "Vegtables"                            
      And I have a Section "Flowers"                              
      And I save everything                                                    

Scenario: Section section Button Exists
    Given I am on "/section/" 
     Then I should see a "table#section-list" element
      And I should see "Vegtables" in the "#section-list" element
      And I should see a "Section section button for Vegtables" element
      And I should see "Flowers" in the "#section-list" element
      And I should see a "Section section button for Flowers" element
    
Scenario: Show an Empty Section
    Given I am on "/section/" 
     Then I should see a "Section section button for Vegtables" element
     When I follow "Section section link for Vegtables"
     Then I should be on "Section section page for Vegtables"
      And I should see a "table#section" element
      And I should see "Vegtables" in the "#section" element
     When I follow "back"
     Then I should be on "/section/"

Scenario: Show a Full Section
    Given I am on "/section/" 
     Then I should see a "Section section button for Vegtables" element
     When I follow "Section section link for Vegtables"
     Then I should be on "Section section page for Vegtables"
      And I should see a "table#section" element
      And I should see "Vegtables" in the "#section" element
     When I follow "back"
     Then I should be on "/section/"

