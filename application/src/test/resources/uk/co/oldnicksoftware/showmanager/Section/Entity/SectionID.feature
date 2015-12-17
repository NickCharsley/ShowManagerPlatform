Feature: Sections have Enique IDs
    In order to manage Sections
    As a Database
    I need a way to uniquely key Sections

Background:
        Given I have an Empty Database

Scenario: Place Part  Type has a ID Field
    Given I have a Section "Vegtables"
     Then Section "Vegtables" "ID" is not writableable
      And Section "Vegtables" "ID" is readable

Scenario: ID is unique (but only when saved?)
    Given I have a Section "Vegtables"
      And I have a Section "Flowers"
     Then Section "Vegtables" "ID" is ""
      And Section "Flowers" "ID" is ""
     When I save everything
     Then Section "Vegtables" "ID" is a UUID
      And Section "Flowers" "ID" is a UUID
     Then Section "Vegtables" and "Flowers" have different "ID" values