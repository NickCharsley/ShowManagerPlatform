Feature: ExhibitionSectionClassifications Have Names, which are usually numbers
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need a Description to call the ExhibitionSectionClassification

Background:
    Given I have an Empty Database
      And I have an Exhibition "Spring Show" Section "Vegtables" Named "1"  
      And I have an ExhibitionSection "Spring Show':'1" Classification "Beetroot" Named "B" 
      And I have an ExhibitionSection "Spring Show':'1" Classification "Carrot" Named "A"

Scenario: ExhibitionSectionClassifications have Description
    Given I Describe ExhibitionSectionClassification "Spring Show':'1':'A" as "Description"  
     Then ExhibitionSectionClassification "Spring Show':'1':'A" "Description" is writableable
      And ExhibitionSectionClassification "Spring Show':'1':'A" "Description" is readable    
      And ExhibitionSectionClassification "Spring Show':'1':'A" is Described as "Description"

Scenario: ExhibitionSectionClassifications Description are not required
    Given ExhibitionSectionClassification "Spring Show':'1':'A" is Described as ""
     Then ExhibitionSectionClassification "Spring Show':'1':'A" is valid

Scenario: ExhibitionSectionClassification Description are not unique
    Given I Describe ExhibitionSectionClassification "Spring Show':'1':'A" as "Description"
      And I save everything
     Then I can Describe ExhibitionSectionClassification "Spring Show':'1':'B" as "Description"
      And ExhibitionSectionClassification "Spring Show':'1':'B" is valid
     