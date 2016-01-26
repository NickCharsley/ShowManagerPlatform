Feature: Edit Exhibition
    In order to manage Exhibitions
    As a User
    I need to be able to rename an Exhibition

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"   
      And I Make Exhibition "Spring Show 2012" Default                               
      And I refresh the Exhibition List                                                   

Scenario: Display a Non Default Exhibition
    Given I edit Exhibition "Spring Show 2011"
     Then the Exhibitions name is "Spring Show 2011"
      And the Exhibition is not the Default

Scenario: Display a Default Exhibition
    Given I edit Exhibition "Spring Show 2012"
     Then the Exhibitions name is "Spring Show 2012"
      And the Exhibition is the Default

Scenario: Edit a Non Default Exhibition
    Given I edit Exhibition "Spring Show 2011"
     When I set the Exhibitions name to "Updated Spring Show 2011"     
      And I save the edits 
     Then The Exhibition List contains "Updated Spring Show 2011"
      And The Exhibition List dosn't contain "Spring Show 2011"

Scenario: Edit a Default Exhibition
    Given I edit Exhibition "Spring Show 2012"
     When I set the Exhibitions name to "Updated Spring Show 2012"     
      And I save the edits 
     Then The Exhibition List contains "Updated Spring Show 2012 (Default)"
      And The Exhibition List dosn't contain "Spring Show 2012 (Default)"

Scenario: Edit a Non Default Exhibition Making it Default
    Given I edit Exhibition "Spring Show 2011"
     When I make the Exhibition the Default
      And I save the edits 
     Then The Exhibition List contains "Spring Show 2011 (Default)"
      And The Exhibition List contains "Spring Show 2012"
      And The Exhibition List dosn't contain "Spring Show 2012 (Default)"


Scenario: Edit a Default Exhibition Removing Default
    Given I edit Exhibition "Spring Show 2012"
     When I make the Exhibition no longer Default
      And I save the edits 
     Then The Exhibition List contains "Spring Show 2012"
      And The Exhibition List dosn't contain "Spring Show 2012 (Default)"

Scenario: Cannot Edit an Exhibition to be the same as another Exhibition
    Given I edit Exhibition "Spring Show 2011"
     When I set the Exhibitions name to "Spring Show 2012"     
     Then I cannot save the edits
      And The Exhibition List contains "Spring Show 2011"
      And The Exhibition List contains "Spring Show 2012"
      And I can edit Exhibition "Spring Show 2012"