Feature: ExhibitionSections have Enique IDs
    In order to manage ExhibitionSections
    As a Database
    I need a way to uniquely key ExhibitionSections

Background:
        Given I have an Empty Database
        
Scenario: Place Part  Type has a ID Field
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"  
     Then ExhibitionSection "Spring Show 2011':'1" "ID" is not writableable
      And ExhibitionSection "Spring Show 2011':'1" "ID" is readable

Scenario: ID is unique (but only when saved?)
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"  
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"  
     Then ExhibitionSection "Spring Show 2011':'1" "ID" is ""
      And ExhibitionSection "Spring Show 2011':'1" "ID" is ""
     When I save everything
     Then ExhibitionSection "Spring Show 2011':'1" "ID" is a UUID
      And ExhibitionSection "Spring Show 2011':'2" "ID" is a UUID
     Then ExhibitionSection "Spring Show 2011':'1" and "Spring Show 2011':'2" have different "ID" values