Feature: Exhibition Entity
    In order to manage Exhibitions
    As a Developer
    I need an Entity to Store Exhibitions in

Background:
    Given I have an Empty Database

Scenario: I have an Exhibition Entity
    Given I have an Exhibition "Spring Show 2011"
     Then "Spring Show 2011" is an "Exhibition"

Scenario: Exhibitions can be saved
    Given I have an Exhibition "Spring Show 2011"
     Then I can save "Spring Show 2011"

Scenario: Exhibitions can be loaded
    Given I have an Exhibition "Spring Show 2011"
      And I save "Spring Show 2011"
     Then I can load Exhibition "Spring Show 2011" from "ONSShowManagerBundle"

Scenario: Loaded Exhibitions are the same as Saved Exhibitions
    Given I have an Exhibition "Spring Show 2011"
      And I save "Spring Show 2011"
      And I load Exhibition "Spring Show 2011" from "ONSShowManagerBundle"
     Then loaded Exhibition "Spring Show 2011" is the same as saved Exhibition "Spring Show 2011"
