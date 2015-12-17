Feature: Delete Sections
    In order to manage Sections
    As a User
    I need to be able to remove an Section and its Artefacts

Background:
    Given I have an Empty Database 
      And I have a Section "Vegtables"                            
      And I have a Section "Flowers"                              
      And I save everything                                                    

Scenario: Section Delete Button Exists
    Given I am on "/section/" 
     Then I should see a "table#section-list" element
      And I should see a "Section delete button for Vegtables" element
      And I should see a "Section delete button for Flowers" element
    
Scenario: Delete an Empty Section
    Given I am on "/section/" 
     Then I should see a "Section delete button for Vegtables" element
     When I follow "Section delete link for Vegtables"
     Then I should be on "/section/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Section Vegtables"
      And I should see text matching "Notice: Finished Deleting Section"

Scenario: Delete a Non Existing Section
    Given I am on "/section/NoSection/delete"
     Then I should be on "/section/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Section to Delete"

Scenario: Delete a Full Section
    Given I am on "/section/" 
     Then I should see a "Section delete button for Vegtables" element
     When I follow "Section delete link for Vegtables"
     Then I should be on "/section/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Section Vegtables"
      And I should see text matching "Notice: Finished Deleting Section"
