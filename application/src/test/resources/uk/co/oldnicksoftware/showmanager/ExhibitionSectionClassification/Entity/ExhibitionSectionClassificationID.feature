Feature: ExhibitionSectionClassifications have Enique IDs
    In order to manage ExhibitionSectionClassifications
    As a Database
    I need a way to uniquely key ExhibitionSectionClassifications

Background:
        Given I have an Empty Database
        
Scenario: Place Part Type has a ID Field
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "1"  
      And I have an ExhibitionSection "Spring Show':'1" Classification "Beetroot" Named "B"
     Then ExhibitionSectionClassification "Spring Show':'1':'B" "ID" is not writableable
      And ExhibitionSectionClassification "Spring Show':'1':'B" "ID" is readable

Scenario: ID is unique (but only when saved?)
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "1"  
      And I have an ExhibitionSection "Spring Show':'1" Classification "Beetroot" Named "B"
      And I have an ExhibitionSection "Spring Show':'1" Classification "Carrot" Named "A"
     Then ExhibitionSectionClassification "Spring Show':'1':'A" "ID" is ""
      And ExhibitionSectionClassification "Spring Show':'1':'B" "ID" is ""
     When I save everything
     Then ExhibitionSectionClassification "Spring Show':'1':'A" "ID" is a UUID
      And ExhibitionSectionClassification "Spring Show':'1':'B" "ID" is a UUID
     Then ExhibitionSectionClassification "Spring Show':'1':'B" and "Spring Show':'1':'A" have different "ID" values
