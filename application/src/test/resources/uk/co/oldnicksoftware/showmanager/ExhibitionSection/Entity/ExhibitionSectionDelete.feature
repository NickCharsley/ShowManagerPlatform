Feature: Delete ExhibitionSections
    In order to manage ExhibitionSections
    As a User
    I need to be able to remove an ExhibitionSection and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show" Section "Vegtables" Named "A"
      And I save everything                                                    

Scenario: Exhibition with an ExhibitionSection know it shouldn't be deleted
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     Then I should not delete "Spring Show"

Scenario: Delete a Full Exhibition
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show"
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository

Scenario: Delete an Empty ExhibitionSection
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show':'A"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
