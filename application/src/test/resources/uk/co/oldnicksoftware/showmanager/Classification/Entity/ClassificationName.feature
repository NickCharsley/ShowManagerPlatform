Feature: Classifications Have Names
    In order to manage Classifications
    As a Developer
    I need a Name to call the Classification

Background:
    Given I have an Empty Database

Scenario: Classifications are Identified by thier Name
    Given I have a Classification "Beetroot"
     Then Classification "Beetroot" "Name" is writableable
     And  Classification "Beetroot" "Name" is readable
     And  Classification "Beetroot" "Name" is "Beetroot"

Scenario: Classification Names are unique
    Given I have a Classification "Beetroot"
      And I save "Beetroot"
     Then I cannot have another Classification "Beetroot"

Scenario: Classification Names are Required
    Given I have a Classification "Beetroot"
     Then Classification "Beetroot" is valid
     When I Name Classification "Beetroot" ""
     Then Classification "Beetroot" is invalid
     