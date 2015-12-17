Feature: ExhibitionSectionClassification Entity
    In order to manage ExhibitionSectionClassifications
    As a Developer
    I need an Entity to Store ExhibitionSectionClassifications in

Background:
    Given I have an Empty Database

Scenario: ExhibitionSectionClassifications are made up from ExhibitionSections and Classifications
    Given I have an Exhibition "Spring Show"
      And I have a Section "Vegtables" 
      And I link Exhibition "Spring Show" to Section "Vegtables" as Section Exhibition Named "1" 
      And I have a Classification "Beetroot"
     Then I can link ExhibitionSection "Spring Show':'1" to Classification "Beetroot" as Classification Section Named "1" 

Scenario: I have an ExhibitionSectionClassification Entity
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then "Spring Show':'A" is an "ExhibitionSection"
      And "Spring Show':'A':'B" is an "ExhibitionSectionClassification"

Scenario: ExhibitionSections can be saved
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
     Then I can save "Spring Show':'A':'B"

Scenario: ExhibitionSections can be loaded
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
      And I save everything
     Then I can load ExhibitionSectionClassification "Spring Show':'A':'B" from "ONSShowManagerBundle"

Scenario: Loaded ExhibitionSections are the same as Saved ExhibitionSections
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
      And I save everything
      And I load ExhibitionSectionClassification "Spring Show':'A':'B" from "ONSShowManagerBundle"
     Then loaded ExhibitionSectionClassification "Spring Show':'A':'B" is the same as saved ExhibitionSectionClassification "Spring Show':'A':'B"
