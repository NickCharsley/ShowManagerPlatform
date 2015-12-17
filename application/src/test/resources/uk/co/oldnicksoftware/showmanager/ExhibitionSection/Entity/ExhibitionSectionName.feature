Feature: ExhibitionSections Have Names, which are usually numbers
    In order to manage ExhibitionSections
    As a Developer
    I need a Name to call the ExhibitionSection

Background:
    Given I have an Empty Database

Scenario: ExhibitionSections are Identified by thier Name
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"  
     Then ExhibitionSection "Spring Show 2011':'1" "Name" is writableable
     And  ExhibitionSection "Spring Show 2011':'1" "Name" is readable
     And  ExhibitionSection "Spring Show 2011':'1" "Name" is "1"

Scenario: ExhibitionSection Names are unique
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save "Spring Show 2011':'1"
     Then I cannot link Exhibition "Spring Show 2011" to Section "Vegtables" as Section Exhibition Named "1" 

Scenario: ExhibitionSection Names are unique even for different Section Names
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have a Section "Flowers"
      And I save everything
     Then I cannot link Exhibition "Spring Show 2011" to Section "Flowers" as Section Exhibition Named "1" 

Scenario: If ExhibitionSection Names are unique Section Names can be the same
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have a Section "Flowers"
      And I save everything
     Then I can link Exhibition "Spring Show 2011" to Section "Flowers" as Section Exhibition Named "2" 

Scenario: ExhibitionSection Names are Required
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
     Then ExhibitionSection "Spring Show 2011':'1" is valid
     When I Name ExhibitionSection "Spring Show 2011':'1" ""
     Then ExhibitionSection "Spring Show 2011':'1" is invalid
     