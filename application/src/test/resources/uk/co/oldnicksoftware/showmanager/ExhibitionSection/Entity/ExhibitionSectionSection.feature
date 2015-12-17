Feature: ExhibitionSections Have Sections, which are usually numbers
    In order to manage ExhibitionSections
    As a Developer
    I need a Section to call the ExhibitionSection

Background:
    Given I have an Empty Database

Scenario: ExhibitionSections are Identified by thier Section
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"  
     Then ExhibitionSection "Spring Show 2011':'1" "Section" is writableable
     And  ExhibitionSection "Spring Show 2011':'1" "Section" is readable
     And  ExhibitionSection "Spring Show 2011':'1" "Section" is Section "Vegtables"

Scenario: If ExhibitionSection Sections are unique Section Sections can be the same
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save "Spring Show 2011':'1"
     When I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "2"
     Then I can save "Spring Show 2011':'2"

Scenario: ExhibitionSection Sections are Required
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
     Then ExhibitionSection "Spring Show 2011':'1" is valid
     When I Section ExhibitionSection "Spring Show 2011':'1" ""
     Then ExhibitionSection "Spring Show 2011':'1" is invalid
     