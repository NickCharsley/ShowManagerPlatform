Feature: Delete Exhibitions
    In order to manage Exhibitions
    As a User
    I need to be able to remove an Exhibition and its Artefacts

Background:
    Given I have an Empty Database 

Scenario: Delete an Empty Exhibition
    Given I have an Exhibition "Spring Show 2011"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
     When I delete "Spring Show 2011"
     Then I have 0 Records in the "ONSShowManagerBundle Exhibition" repository

Scenario: Delete a Full Exhibition
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show 2011"
     Then I have 0 Records in the "ONSShowManagerBundle Exhibition" repository
      And I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
