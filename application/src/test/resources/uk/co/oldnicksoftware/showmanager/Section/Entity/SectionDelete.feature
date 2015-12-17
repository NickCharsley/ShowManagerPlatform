Feature: Delete Sections
    In order to manage Sections
    As a User
    I need to be able to remove an Section and its Artefacts

Background:
    Given I have an Empty Database 

Scenario: Delete an Empty Section
    Given I have a Section "Vegtables"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Vegtables"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle Section" repository

Scenario: Delete a Full Section
    Given I have a Section "Vegtables"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Vegtables"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle Section" repository
