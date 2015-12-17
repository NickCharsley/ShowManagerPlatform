Feature: ExhibitionSectionClassifications Have ExhibitionSections
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need a ExhibitionSection to call the ExhibitionSectionClassification

Background:
    Given I have an Empty Database 

Scenario: ExhibitionSectionClassifications are Linked to thier ExhibitionSections
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then ExhibitionSectionClassification "Spring Show':'A':'B" "Section" is writableable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Section" is readable
      And ExhibitionSectionClassification "Spring Show':'A':'B" "Section" is ExhibitionSection "Spring Show':'A"

Scenario: ExhibitionSectionClassification ExhibitionSections are Required
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then ExhibitionSectionClassification "Spring Show':'A':'B" is valid
     When I Section ExhibitionSectionClassification "Spring Show':'A':'B" ""
     Then ExhibitionSectionClassification "Spring Show':'A':'B" is invalid
