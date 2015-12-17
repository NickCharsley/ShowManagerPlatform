Feature: ExhibitionSectionClassifications Have Classifications
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need a Classifications to call the ExhibitionSectionClassification

Background:
    Given I have an Empty Database 

Scenario: ExhibitionSectionClassifications are Linked to thier Classifications
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then ExhibitionSectionClassification "Spring Show':'A':'B" "Classification" is writableable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Classification" is readable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Classification" is Classification "Beetroot"
     
Scenario: ExhibitionSectionClassification Classifications are Required
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then ExhibitionSectionClassification "Spring Show':'A':'B" is valid
     When I Classify ExhibitionSectionClassification "Spring Show':'A':'B" ""
     Then ExhibitionSectionClassification "Spring Show':'A':'B" is invalid
