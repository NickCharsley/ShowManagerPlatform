Feature: Exhibitions Have Names
    In order to manage Exhibitions
    As a Developer
    I need a Name to call the Exhibition

Background:
    Given I have an Empty Database

Scenario: Exhibitions are Identified by thier Name
    Given I have an Exhibition "Spring Show 2011"
     Then Exhibition "Spring Show 2011" "Name" is writableable
     And  Exhibition "Spring Show 2011" "Name" is readable
     And  Exhibition "Spring Show 2011" "Name" is "Spring Show 2011"

Scenario: Exhibition Names are unique
    Given I have an Exhibition "Spring Show 2011"
      And I save "Spring Show 2011"
     Then I cannot have another Exhibition "Spring Show 2011"

Scenario: Exhibition Names are Required
    Given I have an Exhibition "Spring Show 2011"
     Then Exhibition "Spring Show 2011" is valid
     When I Name Exhibition "Spring Show 2011" ""
     Then Exhibition "Spring Show 2011" is invalid
     