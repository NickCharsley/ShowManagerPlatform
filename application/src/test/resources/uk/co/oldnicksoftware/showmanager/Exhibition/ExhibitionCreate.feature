Feature: Create an Exhibition with a New Name
    In order to manage exhibitions
    As a User
    I need to be able to create an exhibition

Background:
    Given I have an Empty Database 

Scenario: New Exhibition Button Exists
    Given I am on "/show/" 
     Then I should see a "a#new_show" element

Scenario: New Exhibition Form Exists
    Given I am on "/show/new" 
     Then I should see a "div#ons_showmanagerbundle_exhibition" element

Scenario: Create a Non Default Exhibition
    Given I am on "/show/new" 
     Then I have 0 Records in the "ONSShowManagerBundle Exhibition" repository
     When I fill in "Name" with "New Show"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show page for New Show"
      And I should see a "table#show" element
      And I should see "New Show" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "Exhibition default button for New Show" element

Scenario: Exhibition Names are Unique
    Given I have an Exhibition "Spring Show 2011"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Exhibition" repository
     When I am on "/show/new" 
      And I fill in "Name" with "Spring Show 2011"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "/show/"
      And I should see a "div#ons_showmanagerbundle_exhibition" element
      And I should see "This value is already used." in the "#ons_showmanagerbundle_exhibition" element

Scenario: Create a Default Exhibition
    Given I am on "/show/new" 
     Then I have 0 Records in the "ONSShowManagerBundle Exhibition" repository
     When I fill in "Name" with "New Show"
     When I check "Default"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show page for New Show"
      And I should see a "table#show" element
      And I should see "New Show" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"
      And I should not see a "Exhibition default button for New Show" element

Scenario: Create a Non Default Exhibition While another Is a Default
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I Make Exhibition "Spring Show 2011" Default    
      And I save everything                                                    
     When I am on "/show/" 
      And I should not see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
     Then I have 2 Records in the "ONSShowManagerBundle Exhibition" repository
     When I am on "/show/new" 
      And I fill in "Name" with "New Show"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show page for New Show"
      And I should see a "table#show" element
      And I should see "New Show" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"
      And I should see a "Exhibition default button for New Show" element
      And I should not see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element

Scenario: Create a Default Exhibition While another Is a Default
    Given I have an Exhibition "Spring Show 2011"
      And I have an Exhibition "Spring Show 2012"
      And I Make Exhibition "Spring Show 2011" Default    
      And I save everything                                                    
     When I am on "/show/" 
      And I should not see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
     Then I have 2 Records in the "ONSShowManagerBundle Exhibition" repository
     When I am on "/show/new" 
      And I fill in "Name" with "New Show"
     When I check "Default"
      And I press "ons_showmanagerbundle_exhibition_submit"
     Then I should be on "Exhibition show page for New Show"
      And I should see a "table#show" element
      And I should see "New Show" in the "#show" element
     When I follow "back"
     Then I should be on "/show/"
      And I should not see a "Exhibition default button for New Show" element
      And I should see a "Exhibition default button for Spring Show 2011" element
      And I should see a "Exhibition default button for Spring Show 2012" element
