Feature: ExhibitionSections Entity
    In order to manage ExhibitionSections
    As a Developer
    I need an Entity to Store ExhibitionSections in

Background:
    Given I have an Empty Database

Scenario: ExhibitionSections are made up from Exhibitions and Sections
    Given I have an Exhibition "Spring Show 2011"
      And I have a Section "Vegtables" 
     Then I can link Exhibition "Spring Show 2011" to Section "Vegtables" as Section Exhibition Named "1" 
    

Scenario: I have an ExhibitionSection Entity
    Given I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
     Then "Spring Show':'A" is an "ExhibitionSection"

Scenario: ExhibitionSections can be saved
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
     Then I can save "Spring Show 2011':'1"

Scenario: ExhibitionSections can be loaded
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save everything
     Then I can load ExhibitionSection "Spring Show 2011':'1" from "ONSShowManagerBundle"

Scenario: Loaded ExhibitionSections are the same as Saved ExhibitionSections
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save everything
      And I load ExhibitionSection "Spring Show 2011':'1" from "ONSShowManagerBundle"
     Then loaded ExhibitionSection "Spring Show 2011':'1" is the same as saved ExhibitionSection "Spring Show 2011':'1"
