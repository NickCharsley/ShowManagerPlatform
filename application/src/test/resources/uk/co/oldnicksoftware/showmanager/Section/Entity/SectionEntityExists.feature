Feature: Section Entity
    In order to manage Sections
    As a Developer
    I need an Entity to Store Sections in

Background:
    Given I have an Empty Database

Scenario: I have a Section Entity
    Given I have a Section "Vegtables"
     Then "Vegtables" is an "Section"

Scenario: Sections can be saved
    Given I have a Section "Vegtables"
     Then I can save "Vegtables"

Scenario: Sections can be loaded
    Given I have a Section "Vegtables"
      And I save "Vegtables"
     Then I can load Section "Vegtables" from "ONSShowManagerBundle"

Scenario: Loaded Sections are the same as Saved Sections
    Given I have a Section "Vegtables"
      And I save "Vegtables"
      And I load Section "Vegtables" from "ONSShowManagerBundle"
     Then loaded Section "Vegtables" is the same as saved Section "Vegtables"
