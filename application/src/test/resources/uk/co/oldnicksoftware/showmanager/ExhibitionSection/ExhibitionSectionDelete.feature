Feature: Delete ExhibitionSections
    In order to manage ExhibitionSections
    As a User
    I need to be able to remove an ExhibitionSection and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011" Section "1" Named "Vegtables"
      And I have an Exhibition "Spring Show 2011" Section "2" Named "Flowers"
      And I refresh the Exhibition List


Scenario: Cancel Deleting an Exhibition Section
    Given I request Exhibition "Spring Show 2011" Section "2] Flowers" be deleted
     Then I should be asked to confirm the deletion
     When I don't confirm the deletion  
     Then The Exhibition List item "Spring Show 2011" has a sub item "2] Flowers" 

Scenario: Delete an Empty ExhibitionSection
    Given I request Exhibition "Spring Show 2011" Section "2] Flowers" be deleted
     Then I should be asked to confirm the deletion
     When I confirm the deletion  
     Then The Exhibition List item "Spring Show 2011" has no sub item "2] Flowers"
@tbc
Scenario: Delete a Full ExhibitionSection
    Given I have Full Exhibition "Spring Show 2011"      
      And I request Exhibition "Spring Show 2011" Section "2] Flowers" be deleted
     Then I should be asked to confirm the deletion
     When I confirm the deletion  
     Then The Exhibition List item "Spring Show 2011" has no sub item "2] Flowers"
