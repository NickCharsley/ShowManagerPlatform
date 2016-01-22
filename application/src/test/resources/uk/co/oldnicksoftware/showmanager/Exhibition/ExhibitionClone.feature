@tbc
Feature: Clone Exhibition with a New Name
    In order to rapidly set up new exhibitions
    As a User
    I need to beable to clone an exhibition with everything but Results

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"                              
      And I refresh the Exhibition List                                                 

Scenario: Exhibition Clone Button Exists
    Given I have a "ExhibitionList Window" Panel 
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see "Spring Show 2011" in the "#show-list" element
      And I should see a "Exhibition clone button for Spring Show 2011" element
      And I should see "Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition clone button for Spring Show 2012" element
    
Scenario: Clone an Empty Exhibition
    Given I have a "ExhibitionList Window" Panel 
     Then I should see a "Exhibition clone button for Spring Show 2011" element
     When I follow "Exhibition clone link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cloning Show Spring Show 2011 to Spring Show 2011 \[clone\]"
      And I should see text matching "Notice: Finished Cloning Show"
      And The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see "Spring Show 2011 clone" in the "#show-list" element

Scenario: Clone a Full Exhibition
    Given I have a "ExhibitionList Window" Panel 
     Then I should see a "Exhibition clone button for Spring Show 2011" element
     When I follow "Exhibition clone link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cloning Show Spring Show 2011 to Spring Show 2011 \[clone\]"
      And I should see text matching "Notice: Finished Cloning Show"
      And The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see "Spring Show 2011 clone" in the "#show-list" element
