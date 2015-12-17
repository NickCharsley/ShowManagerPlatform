Feature: ExhibitionSections Have Exhibitions
    In order to manage ExhibitionSections
    As a Developer
    I need a Exhibition to call the ExhibitionSection

Background:
    Given I have an Empty Database

Scenario: ExhibitionSections are Linked to thier Exhibition
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"  
     Then ExhibitionSection "Spring Show 2011':'1" "Exhibition" is writableable
     And  ExhibitionSection "Spring Show 2011':'1" "Exhibition" is readable
     And  ExhibitionSection "Spring Show 2011':'1" "Exhibition" is Exhibition "Spring Show 2011"

Scenario: ExhibitionSection Exhibitions are Required
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
     Then ExhibitionSection "Spring Show 2011':'1" is valid
     When I Exhibition ExhibitionSection "Spring Show 2011':'1" ""
     Then ExhibitionSection "Spring Show 2011':'1" is invalid
     