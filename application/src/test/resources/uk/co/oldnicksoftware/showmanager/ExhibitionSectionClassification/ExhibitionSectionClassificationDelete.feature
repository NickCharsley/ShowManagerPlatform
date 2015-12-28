@tbc
Feature: Delete ExhibitionSectionClassifications
    In order to manage ExhibitionSectionClassifications
    As a User
    I need to be able to remove an ExhibitionSectionClassification and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Onion" Named "A"  
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Beetroot" Named "B"  
      And I have an ExhibitionSection "Spring Show 2011':'2" Classification "Rose" Named "C" 
      And I Describe ExhibitionSectionClassification "Spring Show 2011':'2':'C" as "Description"  
      And I Make Exhibition "Spring Show 2011" Default
      And I save everything

Scenario: ExhibitionSectionClassification Delete Button Exists
    Given I am on "/exhibitionsectionclassification/" 
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see a "ExhibitionSectionClassification delete button for 'Spring Show 2011':'1':'A'" element
      And I should see a "ExhibitionSectionClassification delete button for 'Spring Show 2011':'1':'B'" element
      And I should see a "ExhibitionSectionClassification delete button for 'Spring Show 2011':'2':'C'" element
    
Scenario: Delete an ExhibitionSectionClassification
    Given I am on "/exhibitionsectionclassification/" 
     Then I should see a "ExhibitionSectionClassification delete button for 'Spring Show 2011':'1':'A'" element
     When I follow "ExhibitionSectionClassification delete link for 'Spring Show 2011':'1':'A'"
     Then I should be on "/exhibitionsectionclassification/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Spring Show 2011"
      And I should see text matching "Notice: Deleting Spring Show 2011 Section 1\) Vegtables"
      And I should see text matching "Notice: Deleting Spring Show 2011 Section 1\) Vegtables Class A\) Onion"
      And I should see text matching "Notice: Finished Deleting Exhibition Section"

Scenario: Delete a Non Existing ExhibitionSectionClassification
    Given I am on "/exhibitionsectionclassification/NoExhibitionSectionClassification/delete"
     Then I should be on "/exhibitionsectionclassification/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Exhibition Section to Delete"
