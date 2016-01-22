@tbc
Feature: Exhibitions Have a Default Flag
    In order to manage Exhibitions
    As a User
    I need a Name to call the Exhibition

Background:
    Given I have an Empty Database
      And I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I have an Exhibition "Spring Show 2013"
      And I have an Exhibition "Spring Show 2014"
      And I save everything                                                    

Scenario: Exhibition Delete Button Exists
    Given I have a "ExhibitionList Window" Panel 
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element

Scenario: Exhibition Default Flag is set by clicking it
    Given I have a "ExhibitionList Window" Panel 
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element
     When I follow "Exhibition default link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Set Show Spring Show 2011 as Default"
      And I should not see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element

Scenario: Only One Exhibition can have Default Flag set
    Given I have a "ExhibitionList Window" Panel 
     Then The "ExhibitionList Window" Panel's ROOT NODE is "Exhibitions"
      And I should see a "Exhibition default button for Spring Show 2011" element
     When I follow "Exhibition default link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Set Show Spring Show 2011 as Default"
      And I should not see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element
     When I follow "Exhibition default link for Spring Show 2012"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Set Show Spring Show 2012 as Default"
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should not see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element
     When I follow "Exhibition default link for Spring Show 2013"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Set Show Spring Show 2013 as Default"
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should not see a "Exhibition default button for Spring Show 2013" element
      And I should see a "Exhibition default button for Spring Show 2014" element
     When I follow "Exhibition default link for Spring Show 2014"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Set Show Spring Show 2014 as Default"
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
      And I should see a "Exhibition default button for Spring Show 2013" element
      And I should not see a "Exhibition default button for Spring Show 2014" element
