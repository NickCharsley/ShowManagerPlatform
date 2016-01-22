@tbc
Feature: Create an Exhibition with a New Name
    In order to manage exhibitions
    As a User
    I need to be able to access the New Exhibition Dialog

Background:
    Given I have an Empty Database 
      And I have a "ExhibitionList Window" Panel 
      And I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I refresh the Exhibition List

Scenario: Toolbar Icon enabled for Root Node
    If we select the Exihibition List Root Node, New is enabled
    Given "Exhibitions" is selected in the Exhibition List 
     Then the "&File" toolbar Item "New" is enabled

Scenario: Toolbar Icon enabled for Child Node
    If we select any Exhibition List Child Node, New is enabled
    Given "Spring Show 2011" is selected in the Exhibition List 
     Then the "&File" toolbar Item "New" is enabled

Scenario: Toolbar Icon enabled for Exhibition Editor Window
    If we select any field in the Exhibition Editor Window, New is enabled
    Given "Spring Show 2011" is selected in the Exhibition List 
      And I select the "ExhibitionEditor Window" Panel's "Name:" 
     Then the "&File" toolbar Item "New" is enabled

Scenario: New Exhibition Popup Available
    Only the Root Node of the Exhibition List has the New Exhibition Menu
    Given "Exhibitions" is selected in the Exhibition List 
     Then The "ExhibitionList Window" Panel's ROOT NODE has a popup menu item "New Exhibition"
      And The "ExhibitionList Window" Panel's FIRST NODE doesnot have a popup menu item "New Exhibition"
      And The "ExhibitionList Window" Panel's LAST NODE doesnot have a popup menu item "New Exhibition"

Scenario: New Exhibition Menu is Available
    Only the Root Node of the Exhibition List has the New Exhibition Menu
    Then the main "File" menu contains "New Exhibition"
