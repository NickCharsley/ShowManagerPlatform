Feature: Delete Exhibitions
    In order to manage Exhibitions
    As a User
    I need to be able to remove an Exhibition and its Artefacts

Background:
    Given I have an Empty Database 
      And I have a "ExhibitionList Window" Panel 
      And I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I refresh the Exhibition List

Scenario: Cancel Deleting an Exhibition
    Given I request Exhibition "Spring Show 2011" be deleted
     Then I should be asked to confirm the deletion
     When I don't confirm the deletion  
     Then The Exhibition List contains "Spring Show 2011"
    
Scenario: Delete an Empty Exhibition
    Given I request Exhibition "Spring Show 2011" be deleted
     When I confirm the deletion  
     Then The Exhibition List dosn't contain "Spring Show 2011"
@tbc
Scenario: Delete a Full Exhibition
    Given I have Full Exhibition "Spring Show 2011"      
      And I request Exhibition "Spring Show 2011" be deleted
     When I confirm the deletion  
     Then The Exhibition List dosn't contain "Spring Show 2011"
