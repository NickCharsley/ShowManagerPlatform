Feature: Clone Exhibition with a New Name
    In order to rapidly set up new exhibitions
    As a User
    I need to beable to clone an exhibition with everything but Results

Background:
    Given I have an Empty Database 

Scenario: Clone an Empty Exhibition
    Given I have an Exhibition "Spring Show 2011"                            
      And I save "Spring Show 2011"                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
     When I clone "Spring Show 2011"
     Then Exhibition "Spring Show 2011" is valid
      And Exhibition "Spring Show 2011 clone" is valid
      And I save everything                                                    
      And I have 2 Records in the "ONSShowManagerBundle Exhibition" repository

Scenario: Clone a Full Exhibition
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I clone "Spring Show 2011"
     Then Exhibition "Spring Show 2011" is valid
      And Exhibition "Spring Show 2011 clone" is valid
      And I save everything                                                    
     Then I have 2 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 2 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository

Scenario: Clone a Default Exhibition
    Given I have an Exhibition "Spring Show 2011"   
      And I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I clone "Spring Show 2011"
     Then Exhibition "Spring Show 2011" is valid
      And Exhibition "Spring Show 2011 clone" is valid
      And Exhibition "Spring Show 2011" "Default" is "true"
      And Exhibition "Spring Show 2011 clone" "Default" is "false"
