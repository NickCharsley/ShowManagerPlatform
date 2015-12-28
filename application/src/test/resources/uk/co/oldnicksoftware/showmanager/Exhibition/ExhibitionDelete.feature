@tbc
Feature: Delete Exhibitions
    In order to manage Exhibitions
    As a User
    I need to be able to remove an Exhibition and its Artefacts

Background:
    Given I have an Empty Database 
      And I have an Exhibition "Spring Show 2011"                            
      And I have an Exhibition "Spring Show 2012"                              
      And I save everything                                                    

Scenario: Exhibition Delete Button Exists
    Given I am on "/show/" 
     Then I should see a "table#show-list" element
      And I should see a "Exhibition delete button for Spring Show 2011" element
      And I should see a "Exhibition delete button for Spring Show 2012" element
    
Scenario: Delete an Empty Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition delete button for Spring Show 2011" element
     When I follow "Exhibition delete link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Show Spring Show 2011"
      And I should see text matching "Notice: Finished Deleting Show"

Scenario: Delete a Non Existing Exhibition
    Given I am on "/show/NoShow/delete"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Exhibition to Delete"

Scenario: Delete a Full Exhibition
    Given I am on "/show/" 
     Then I should see a "Exhibition delete button for Spring Show 2011" element
     When I follow "Exhibition delete link for Spring Show 2011"
     Then I should be on "/show/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Show Spring Show 2011"
      And I should see text matching "Notice: Finished Deleting Show"
