Feature: Exhibitions Have a Default Flag
    In order to manage Exhibitions
    As a Developer
    I need to set an Exhibition as the default

Background:
    Given I have an Empty Database

Scenario: Exhibitions have a Default Flag
    Given I have an Exhibition "Spring Show 2011"
     Then Exhibition "Spring Show 2011" "Default" is writableable
     And  Exhibition "Spring Show 2011" "Default" is readable

Scenario: Exhibition Default Flag defaults to 0
    Given I have an Exhibition "Spring Show 2011"
     Then Exhibition "Spring Show 2011" "Default" is "false"

Scenario: Exhibition Default Flag can be set via the special default methods
    Given I have an Exhibition "Spring Show 2011"
      And Exhibition "Spring Show 2011" "Default" is "false"
     When I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I Remove Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"

Scenario: Exhibition Default Flag can be set to true by 'true'
    Given I have an Exhibition "Spring Show 2011"
      And Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "true"
     Then Exhibition "Spring Show 2011" "Default" is "true"

Scenario: Exhibition Default Flag can be set to false by 'false'
    Given I have an Exhibition "Spring Show 2011"
     When I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I set Exhibition "Spring Show 2011" default to "false"
     Then Exhibition "Spring Show 2011" "Default" is "false"

Scenario: Exhibition Default Flag can be set to true by non zero numbers
    Given I have an Exhibition "Spring Show 2011"
      And Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "true"
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I Remove Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "-1"
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I Remove Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "-3.75"
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I Remove Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "50"
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I Remove Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
     When I set Exhibition "Spring Show 2011" default to "Cat"
     Then Exhibition "Spring Show 2011" "Default" is "true"

Scenario: Exhibition Default Flag can be set to false by zero and empty values
    Given I have an Exhibition "Spring Show 2011"
     When I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I set Exhibition "Spring Show 2011" default to "false"
     Then Exhibition "Spring Show 2011" "Default" is "false"
     When I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I set Exhibition "Spring Show 2011" default to ""
     Then Exhibition "Spring Show 2011" "Default" is "false"

Scenario: Only One Exhibition can have Default Flag set
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I have an Exhibition "Spring Show 2013"
      And I have an Exhibition "Spring Show 2014"
     Then Exhibition "Spring Show 2011" "Default" is "false"
      And Exhibition "Spring Show 2012" "Default" is "false"
      And Exhibition "Spring Show 2013" "Default" is "false"
      And Exhibition "Spring Show 2014" "Default" is "false"
     When I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
      And Exhibition "Spring Show 2012" "Default" is "false"
      And Exhibition "Spring Show 2013" "Default" is "false"
      And Exhibition "Spring Show 2014" "Default" is "false"
     When I Make Exhibition "Spring Show 2012" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
      And Exhibition "Spring Show 2012" "Default" is "true"
      And Exhibition "Spring Show 2013" "Default" is "false"
      And Exhibition "Spring Show 2014" "Default" is "false"
     When I Make Exhibition "Spring Show 2013" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
      And Exhibition "Spring Show 2012" "Default" is "false"
      And Exhibition "Spring Show 2013" "Default" is "true"
      And Exhibition "Spring Show 2014" "Default" is "false"
     When I Make Exhibition "Spring Show 2014" Default    
     Then Exhibition "Spring Show 2011" "Default" is "false"
      And Exhibition "Spring Show 2012" "Default" is "false"
      And Exhibition "Spring Show 2013" "Default" is "false"
      And Exhibition "Spring Show 2014" "Default" is "true"

Scenario: Clone a Default Exhibition
    Given I have an Exhibition "Spring Show 2011"   
      And I Make Exhibition "Spring Show 2011" Default    
     Then Exhibition "Spring Show 2011" "Default" is "true"
     When I clone "Spring Show 2011"
     Then Exhibition "Spring Show 2011" is valid
      And Exhibition "Spring Show 2011 clone" is valid
      And Exhibition "Spring Show 2011" "Default" is "true"
      And Exhibition "Spring Show 2011 clone" "Default" is "false"
