Feature: Delete ExhibitionSectionClassifications
    In order to manage ExhibitionSectionClassifications
    As a User
    I need to be able to remove an ExhibitionSectionClassification and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show" Section "Vegtables" Named "A"  
      And I have an ExhibitionSection "Spring Show':'A" Classification "Beetroot" Named "B"  
      And I save everything

Scenario: Delete an ExhibitionSectionClassification
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show':'A':'B"
      And I save everything                                                    
     Then I have 0 Records in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository

Scenario: ExhibitionSection with a ExhibitionSectionClassification know it shouldn't be deleted
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     Then I should not delete "Spring Show':'A"

Scenario: Delete a Full Exhibition is Ignored
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show"
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository

Scenario: Delete a Full ExhibitionSection is Ignored
    Given I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository
     When I delete "Spring Show':'A"
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle ExhibitionSectionClassification" repository
      And I have 1 Record in the "ONSShowManagerBundle ExhibitionSection" repository
      And I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
      And I have 1 Record in the "ONSShowManagerBundle Section" repository

