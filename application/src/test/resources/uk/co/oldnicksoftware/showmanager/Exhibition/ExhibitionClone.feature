@tbc
Feature: Clone Exhibition with a New Name
    In order to rapidly set up new exhibitions
    As a User
    I need to beable to clone an exhibition with everything but Results

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"                              
      And I save everything                                                    

Scenario: Exhibition Clone Button Exists
    Given I am on "/show/" 
     Then I should see a "table#show-list" element
      And I should see "Spring Show 2011" in the "#show-list" element
      And I should see a "Exhibition clone button for Spring Show 2011" element
      And I should see "Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition clone button for Spring Show 2012" element
    
Scenario: Clone an Empty Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition clone button for Spring Show 2011" element
     When I follow "Exhibition clone link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cloning Show Spring Show 2011 to Spring Show 2011 \[clone\]"
      And I should see text matching "Notice: Finished Cloning Show"
      And I should see a "table#show-list" element
      And I should see "Spring Show 2011 clone" in the "#show-list" element

Scenario: Clone a Non Existing Exhibition
    Given I am on "/show/NoShow/clone"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Exhibition to Clone"

Scenario: Clone a Full Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition clone button for Spring Show 2011" element
     When I follow "Exhibition clone link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cloning Show Spring Show 2011 to Spring Show 2011 \[clone\]"
      And I should see text matching "Notice: Finished Cloning Show"
      And I should see a "table#show-list" element
      And I should see "Spring Show 2011 clone" in the "#show-list" element
