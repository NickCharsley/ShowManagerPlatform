Feature: Delete ExhibitionSections
    In order to manage ExhibitionSections
    As a User
    I need to be able to remove an ExhibitionSection and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything                                                    

Scenario: ExhibitionSection Delete Button Exists
    Given I am on "/exhibitionsection/" 
     Then I should see a "table#exhibitionsection-list" element
      And I should see a "ExhibitionSection delete button for 'Spring Show 2011':'1'" element
      And I should see a "ExhibitionSection delete button for 'Spring Show 2011':'2'" element
    
Scenario: Delete an Empty ExhibitionSection
    Given I am on "/exhibitionsection/" 
     Then I should see a "ExhibitionSection delete button for 'Spring Show 2011':'1'" element
     When I follow "ExhibitionSection delete link for 'Spring Show 2011':'1'"
     Then I should be on "/exhibitionsection/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Spring Show 2011 Section 1\) Vegtables"
      And I should see text matching "Notice: Finished Deleting Exhibition Section"

Scenario: Delete a Non Existing ExhibitionSection
    Given I am on "/exhibitionsection/NoExhibitionSection/delete"
     Then I should be on "/exhibitionsection/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Exhibition Section to Delete"

Scenario: Delete a Full ExhibitionSection
    Given I am on "/exhibitionsection/" 
     Then I should see a "ExhibitionSection delete button for 'Spring Show 2011':'2'" element
     When I follow "ExhibitionSection delete link for 'Spring Show 2011':'2'"
     Then I should be on "/exhibitionsection/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Spring Show 2011 Section 2\) Flowers"
      And I should see text matching "Notice: Finished Deleting Exhibition Section"
