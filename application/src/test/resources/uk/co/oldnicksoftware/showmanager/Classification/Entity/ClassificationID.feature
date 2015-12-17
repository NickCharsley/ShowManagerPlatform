Feature: Classifications have Enique IDs
    In order to manage Classifications
    As a Database
    I need a way to uniquely key Classifications

Background:
        Given I have an Empty Database

Scenario: Place Part  Type has a ID Field
    Given I have a Classification "Beetroot"
     Then Classification "Beetroot" "ID" is not writableable
      And Classification "Beetroot" "ID" is readable

Scenario: ID is unique (but only when saved?)
    Given I have a Classification "Beetroot"
      And I have a Classification "Onions"
     Then Classification "Beetroot" "ID" is ""
      And Classification "Onions" "ID" is ""
     When I save everything
     Then Classification "Beetroot" "ID" is a UUID
      And Classification "Onions" "ID" is a UUID
     Then Classification "Beetroot" and "Onions" have different "ID" values