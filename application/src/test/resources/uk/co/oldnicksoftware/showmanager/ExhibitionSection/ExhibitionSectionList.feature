Feature: ExhibitionSection List Page
    In order to manage ExhibitionSections
    As a User
    I need a Page detailing the Current ExhibitionSections

Background:
    Given I have an Empty Database

Scenario: Exhibitions With One ExhibitionSection are Expandabe
    Given I have an Exhibition "Spring Show 2011"
      And I refresh the Exhibition List
     Then The Exhibition List contains "Spring Show 2011"
      And The Exhibition List item "Spring Show 2011" is not expandable

Scenario: Exhibitions With One ExhibitionSection are Expandabe
    Given I have an Exhibition "Spring Show 2011" Section "1" Named "Vegtables"
      And I refresh the Exhibition List
     Then The Exhibition List contains "Spring Show 2011"
      And The Exhibition List item "Spring Show 2011" is expandable

Scenario: Exhibitions With Many ExhibitionSection are Expandabe
    Given I have an Exhibition "Spring Show 2011" Section "1" Named "Vegtables"
      And I have an Exhibition "Spring Show 2011" Section "2" Named "Flowers"
      And I refresh the Exhibition List
     Then The Exhibition List item "Spring Show 2011" is expandable

Scenario: Exhibitions With One ExhibitionSection has Correct ExhibitionSections
    Given I have an Exhibition "Spring Show 2011" Section "1" Named "Vegtables"
      And I refresh the Exhibition List
     Then The Exhibition List contains "Spring Show 2011"
      And The Exhibition List item "Spring Show 2011" has a sub item "1] Vegtables" 

Scenario: Exhibitions With Many ExhibitionSections has Correct ExhibitionSections
    Given I have an Exhibition "Spring Show 2011" Section "1" Named "Vegtables"
      And I have an Exhibition "Spring Show 2011" Section "2" Named "Flowers"
      And I refresh the Exhibition List
     Then The Exhibition List contains "Spring Show 2011"
      And The Exhibition List item "Spring Show 2011" has a sub item "1] Vegtables" 
      And The Exhibition List item "Spring Show 2011" has a sub item "2] Flowers" 
