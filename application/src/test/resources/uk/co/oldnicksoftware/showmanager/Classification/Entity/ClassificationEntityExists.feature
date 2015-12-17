@wip
Feature: Classification Entity
    In order to manage Classifications
    As a Developer
    I need an Entity to Store Classifications in

Background:
    Given I have an Empty Database

Scenario: I have a Classification Entity
    Given I have a Classification "Beetroot"
     Then "Beetroot" is a "Classification"

Scenario: Classifications can be saved
    Given I have a Classification "Beetroot"
     Then I can save "Beetroot"

Scenario: Classifications can be loaded
    Given I have a Classification "Beetroot"
      And I save "Beetroot"
     Then I can load Classification "Beetroot" from "ONSShowManagerBundle"

Scenario: Loaded Classifications are the same as Saved Classifications
    Given I have a Classification "Beetroot"
      And I save "Beetroot"
      And I load Classification "Beetroot" from "ONSShowManagerBundle"
     Then loaded Classification "Beetroot" is the same as saved Classification "Beetroot"
