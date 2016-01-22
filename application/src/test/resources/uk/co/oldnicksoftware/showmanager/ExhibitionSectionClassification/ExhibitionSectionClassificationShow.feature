@tbc
Feature: Show ExhibitionSectionClassification
    In order to manage ExhibitionSectionClassifications
    As a User
    I need to be able to view an ExhibitionSectionClassification

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Onion" Named "A"  
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Beetroot" Named "B"  
      And I have an ExhibitionSection "Spring Show 2011':'2" Classification "Rose" Named "C" 
      And I Describe ExhibitionSectionClassification "Spring Show 2011':'2':'C" as "Description"  
      And I Make Exhibition "Spring Show 2011" Default

Scenario: If we have no Default Exhibition we bounce back to Show Page
    Given I Remove Exhibition "Spring Show 2011" Default    
      And I save everything                                                    
      And I am on "/exhibitionsectionclassification/None"
     Then I should be on "/show/"

Scenario: ExhibitionSectionClassification show Button Exists
    Given I am on "/exhibitionsectionclassification/" 
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification-list" element
      And I should see "Onion" in the "#exhibitionsectionclassification-list" element
      And I should see "Beetroot" in the "#exhibitionsectionclassification-list" element
      And I should see a "ExhibitionSectionClassification show button for 'Spring Show 2011':'1':'A'" element
      And I should see a "ExhibitionSectionClassification show button for 'Spring Show 2011':'1':'B'" element
      And I should see "Rose" in the "#exhibitionsectionclassification-list" element
      And I should see a "ExhibitionSectionClassification show button for 'Spring Show 2011':'2':'C'" element
    
Scenario: Show an ExhibitionSectionClassification with no Description
    Given I am on "/exhibitionsectionclassification/" 
     Then I should see a "ExhibitionSectionClassification show button for 'Spring Show 2011':'1':'A'" element
     When I follow "ExhibitionSectionClassification show link for 'Spring Show 2011':'1':'A'"
     Then I should be on "ExhibitionSectionClassification exhibitionsectionclassification page for 'Spring Show 2011':'1':'A'"
      And I should see a "table#exhibitionsectionclassification" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification" element
      And I should see "Onion" in the "#exhibitionsectionclassification" element
      And I should not see "(Description)" in the "#exhibitionsectionclassification" element
     When I follow "back"
     Then I should be on "/exhibitionsectionclassification/"

Scenario: Show an ExhibitionSectionClassification with a Description
    Given I am on "/exhibitionsectionclassification/" 
     Then I should see a "ExhibitionSectionClassification show button for 'Spring Show 2011':'2':'C'" element
     When I follow "ExhibitionSectionClassification show link for 'Spring Show 2011':'2':'C'"
     Then I should be on "ExhibitionSectionClassification exhibitionsectionclassification page for 'Spring Show 2011':'2':'C'"
      And I should see a "table#exhibitionsectionclassification" element
      And I should see "Flowers" in the "#exhibitionsectionclassification" element
      And I should see "Rose" in the "#exhibitionsectionclassification" element
      And I should see "(Description)" in the "#exhibitionsectionclassification" element
     When I follow "back"
     Then I should be on "/exhibitionsectionclassification/"

