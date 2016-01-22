Feature: Exhibition List Page
    In order to manage Exhibitions
    As a User
    I need a Page detailing the Current Exhibitions

Background:
    Given I have an Empty Database

Scenario: There are No Exhibitions
    Given I have an "ExhibitionList Window" Panel
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And The "ExhibitionList Window" Panel contains an empty tree of "Exhibitions"

Scenario: There is One Exhibition
    Given I have an Exhibition "Spring Show 2011"
     When I have an "ExhibitionList Window" Panel
      And I refresh the Exhibition List
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And The "ExhibitionList Window" Panel contains a tree of "Exhibition"
      And The Exhibition List contains "Spring Show 2011"

Scenario: There are Many Exhibitions
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
     When I have an "ExhibitionList Window" Panel
      And I refresh the Exhibition List
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And The Exhibition List contains "Spring Show 2011"
      And The Exhibition List contains "Spring Show 2012"
