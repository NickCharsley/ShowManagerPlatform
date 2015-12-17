Feature: Exhibitions have Enique IDs
    In order to manage Exhibitions
    As a Database
    I need a way to uniquely key Exhibitions

Background:
        Given I have an Empty Database

Scenario: Place Part  Type has a ID Field
    Given I have an Exhibition "Spring Show 2011"
     Then Exhibition "Spring Show 2011" "ID" is not writableable
      And Exhibition "Spring Show 2011" "ID" is readable

Scenario: ID is unique (but only when saved?)
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
     Then Exhibition "Spring Show 2011" "ID" is ""
      And Exhibition "Spring Show 2012" "ID" is ""
     When I save everything
     Then Exhibition "Spring Show 2011" "ID" is a UUID
      And Exhibition "Spring Show 2012" "ID" is a UUID
     Then Exhibition "Spring Show 2011" and "Spring Show 2012" have different "ID" values