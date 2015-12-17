Feature: Delete Classifications
    In order to manage Classifications
    As a User
    I need to be able to remove an Classification and its Artefacts

Background:
    Given I have an Empty Database 

Scenario: Delete an Empty Classification
    Given I have a Classification "Beetroot"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Classification" repository
     When I delete "Beetroot"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle Classification" repository

Scenario: Delete a Full Classification
    Given I have a Classification "Beetroot"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Classification" repository
     When I delete "Beetroot"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle Classification" repository
