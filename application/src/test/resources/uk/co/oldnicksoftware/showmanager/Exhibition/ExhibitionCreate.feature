Feature: Create an Exhibition with a New Name
    In order to manage exhibitions
    As a User
    I need to be able to create an exhibition

Background:
    Given I have an Empty Database 
      And I have a "ExhibitionList Window" Panel 
      And I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I refresh the Exhibition List

Scenario: New Exhibition Window appears and can be Canceled
    If we press the New Toolbar button then the correct dialog is displayed
    and it can be cancelled.
    Given I Display the New Exhibition Dialog
      And the New Exhibition Dialog is displayed
     When I Cancel the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden

Scenario: New Exhibitions can be added
    Given I Display the New Exhibition Dialog
      And I set the New Exhibition's Name to "Spring Show 2013"
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden
      And The Exhibition List contains "Spring Show 2013"

Scenario: Exhibition Names must be populated
    Given I Display the New Exhibition Dialog
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog displays the error "Name is compulsory."
      And the New Exhibition Dialog is displayed
     When I Cancel the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden

Scenario: Exhibition Names are Unique
    Given I Display the New Exhibition Dialog
      And I set the New Exhibition's Name to "Spring Show 2011"
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog displays the error "This Exhibition already exists."
      And the New Exhibition Dialog is displayed
     When I Cancel the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden

Scenario: Create a Default Exhibition
    Given I Display the New Exhibition Dialog
      And I set the New Exhibition's Name to "Spring Show 2013"
      And I set the New Exhibition to Default
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden
      And The Exhibition List contains "Spring Show 2013 (Default)"

Scenario: Create a Non Default Exhibition While another Is a Default
    Given I Make Exhibition "Spring Show 2011" Default    
      And I refresh the Exhibition List
     When I Display the New Exhibition Dialog
      And I set the New Exhibition's Name to "Spring Show 2013"
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden
     And The Exhibition List contains "Spring Show 2013"
     And The Exhibition List contains "Spring Show 2011 (Default)"

Scenario: Create a Default Exhibition While another Is a Default
    Given I Make Exhibition "Spring Show 2011" Default    
      And I refresh the Exhibition List                                                    
     When I Display the New Exhibition Dialog
      And I set the New Exhibition's Name to "Spring Show 2013"
      And I set the New Exhibition to Default
      And I submit the New Exhibition Dialog
     Then the New Exhibition Dialog is hidden
     And The Exhibition List contains "Spring Show 2013 (Default)"
     And The Exhibition List contains "Spring Show 2011"
