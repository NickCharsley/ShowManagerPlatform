Feature: Sections Have Names
    In order to manage Sections
    As a Developer
    I need a Name to call the Section

Background:
    Given I have an Empty Database

Scenario: Sections are Identified by thier Name
    Given I have a Section "Vegtables"
     Then Section "Vegtables" "Name" is writableable
     And  Section "Vegtables" "Name" is readable
     And  Section "Vegtables" "Name" is "Vegtables"

Scenario: Section Names are unique
    Given I have a Section "Vegtables"
      And I save "Vegtables"
     Then I cannot have another Section "Vegtables"

Scenario: Section Names are Required
    Given I have a Section "Vegtables"
     Then Section "Vegtables" is valid
     When I Name Section "Vegtables" ""
     Then Section "Vegtables" is invalid
     