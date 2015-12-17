Feature: ExhibitionSectionClassifications Have Names, which are usually numbers
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need a Name to call the ExhibitionSectionClassification

Background:
    Given I have an Empty Database
      And I have an Exhibition "Spring Show" Section "Vegtables" Named "1"  
      And I have an ExhibitionSection "Spring Show':'1" Classification "Beetroot" Named "B" 
      And I have an ExhibitionSection "Spring Show':'1" Classification "Carrot" Named "A"

Scenario: ExhibitionSectionClassifications are Identified by thier Name
    Given ExhibitionSectionClassification "Spring Show':'1':'B" "Name" is writableable
     And  ExhibitionSectionClassification "Spring Show':'1':'B" "Name" is readable
     And  ExhibitionSectionClassification "Spring Show':'1':'B" "Name" is "B"

Scenario: ExhibitionSectionClassification Names are unique
    Given I save "Spring Show':'1':'B"
    Then I cannot link ExhibitionSection "Spring Show':'1" to Classification "Beetroot" as Classification Section Named "A" 

Scenario: ExhibitionSectionClassification Names are unique even for different Section Names
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have a Section "Flowers"
      And I save everything
     Then I cannot link Exhibition "Spring Show 2011" to Section "Flowers" as Section Exhibition Named "1" 

Scenario: If ExhibitionSectionClassification Names are unique Section Names can be the same
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have a Section "Flowers"
      And I save everything
     Then I can link Exhibition "Spring Show 2011" to Section "Flowers" as Section Exhibition Named "2" 

Scenario: ExhibitionSectionClassification Names are Required
    Given ExhibitionSectionClassification "Spring Show':'1':'B" is valid
     When I Name ExhibitionSectionClassification "Spring Show':'1':'B" ""
     Then ExhibitionSectionClassification "Spring Show':'1':'B" is invalid
     