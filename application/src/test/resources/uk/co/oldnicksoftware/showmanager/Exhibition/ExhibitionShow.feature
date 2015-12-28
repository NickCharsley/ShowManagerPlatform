@tbc
Feature: Show Exhibition
    In order to manage Exhibitions
    As a User
    I need to be able to view an Exhibition

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"                              
      And I save everything                                                    

Scenario: Exhibition show Button Exists
    Given I am on "/show/" 
     Then I should see a "table#show-list" element
      And I should see "Spring Show 2011" in the "#show-list" element
      And I should see a "Exhibition show button for Spring Show 2011" element
      And I should see "Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition show button for Spring Show 2012" element
    
Scenario: Show an Empty Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition show button for Spring Show 2011" element
     When I follow "Exhibition show link for Spring Show 2011"
     Then I should be on "Exhibition show page for Spring Show 2011"
      And I should see a "table#show" element
      And I should see "Spring Show 2011" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"

Scenario: Show a Full Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition show button for Spring Show 2011" element
     When I follow "Exhibition show link for Spring Show 2011"
     Then I should be on "Exhibition show page for Spring Show 2011"
      And I should see a "table#show" element
      And I should see "Spring Show 2011" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"

