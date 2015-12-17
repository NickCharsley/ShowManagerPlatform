Feature: ExhibitionSectionClassifications Have Exhibitions
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need a Exhibition to call the ExhibitionSectionClassification

Background:
    Given I have an Empty Database 

Scenario: ExhibitionSectionClassifications are Linked to thier Exhibition
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then ExhibitionSectionClassification "Spring Show':'A':'B" "Exhibition" is not writableable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Exhibition" is readable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Exhibition" is Exhibition "Spring Show"
     