@tbc
Feature: Edit Exhibition
    In order to manage Exhibitions
    As a User
    I need to be able to rename an Exhibition

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"   
      And I Make Exhibition "Spring Show 2012" Default                               
      And I save everything                                                    
     When I am on "/show/" 
     Then I should see a "Exhibition default button for Spring Show 2011" element
      And I should not see a "Exhibition default button for Spring Show 2012" element

Scenario: Exhibition Edit Button Exists
    Given I should see a "table#show-list" element
      And I should see "Spring Show 2011" in the "#show-list" element
      And I should see a "Exhibition edit button for Spring Show 2011" element
      And I should see "Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition edit button for Spring Show 2012" element
    
Scenario: Edit a Non Default Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition edit button for Spring Show 2011" element
     When I follow "Exhibition edit link for Spring Show 2011"
     Then I should be on "Exhibition show edit page for Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2011"
     When I fill in "ons_showmanagerbundle_exhibition_name" with "Updated Spring Show 2011"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show edit page for Updated Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Updated Spring Show 2011"
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "table#show-list" element
      And I should see "Updated Spring Show 2011" in the "#show-list" element
      And I should see a "Exhibition default button for Updated Spring Show 2011" element
      And I should not see a "Exhibition default button for Spring Show 2012" element

Scenario: Edit a Default Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition edit button for Spring Show 2012" element
     When I follow "Exhibition edit link for Spring Show 2012"
     Then I should be on "Exhibition show edit page for Spring Show 2012"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2012"
     When I fill in "ons_showmanagerbundle_exhibition_name" with "Updated Spring Show 2012"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show edit page for Updated Spring Show 2012"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Updated Spring Show 2012"
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "table#show-list" element
      And I should see "Updated Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should not see a "Exhibition default button for Updated Spring Show 2012" element

Scenario: Edit a Non Default Exhibition Making it Default
    Given I am on "/show/" 
     Then I should see a "Exhibition edit button for Spring Show 2011" element
     When I follow "Exhibition edit link for Spring Show 2011"
     Then I should be on "Exhibition show edit page for Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2011"
     When I fill in "ons_showmanagerbundle_exhibition_name" with "Updated Spring Show 2011"
      And I check "Default"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show edit page for Updated Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Updated Spring Show 2011"
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "table#show-list" element
      And I should see "Updated Spring Show 2011" in the "#show-list" element
      And I should not see a "Exhibition default button for Updated Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element

Scenario: Edit a Default Exhibition Removing Default
    Given I am on "/show/" 
     Then I should see a "Exhibition edit button for Spring Show 2012" element
     When I follow "Exhibition edit link for Spring Show 2012"
     Then I should be on "Exhibition show edit page for Spring Show 2012"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2012"
     When I fill in "ons_showmanagerbundle_exhibition_name" with "Updated Spring Show 2012"
      And I uncheck "Default"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show edit page for Updated Spring Show 2012"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Updated Spring Show 2012"
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "table#show-list" element
      And I should see "Updated Spring Show 2012" in the "#show-list" element
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Updated Spring Show 2012" element

Scenario: Cannot Edit an Exhibition to be the same as another Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition edit button for Spring Show 2011" element
     When I follow "Exhibition edit link for Spring Show 2011"
     Then I should be on "Exhibition show edit page for Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2011"
     When I fill in "ons_showmanagerbundle_exhibition_name" with "Spring Show 2012"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show page for Spring Show 2011"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And the "ons_showmanagerbundle_exhibition_name" field should contain "Spring Show 2012"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And I should see "This value is already used." in the "#ons_showmanagerbundle_exhibition" element
