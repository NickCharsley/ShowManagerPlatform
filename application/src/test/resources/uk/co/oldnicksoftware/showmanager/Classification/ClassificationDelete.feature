@tbc
Feature: Delete Classifications
    In order to manage Classifications
    As a User
    I need to be able to remove an Classification and its Artefacts

Background:
    Given I have an Empty Database 
      And I have a Classification "Beetroot"                            
      And I have a Classification "Onions"                              
      And I save everything                                                    

Scenario: Classification Delete Button Exists
    Given I am on "/classification/" 
     Then I should see a "table#classification-list" element
      And I should see a "Classification delete button for Beetroot" element
      And I should see a "Classification delete button for Onions" element
    
Scenario: Delete an Empty Classification
    Given I am on "/classification/" 
     Then I should see a "Classification delete button for Beetroot" element
     When I follow "Classification delete link for Beetroot"
     Then I should be on "/classification/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Classification Beetroot"
      And I should see text matching "Notice: Finished Deleting Classification"

Scenario: Delete a Non Existing Classification
    Given I am on "/classification/NoClassification/delete"
     Then I should be on "/classification/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Cannot Find Classification to Delete"

Scenario: Delete a Full Classification
    Given I am on "/classification/" 
     Then I should see a "Classification delete button for Beetroot" element
     When I follow "Classification delete link for Beetroot"
     Then I should be on "/classification/"
      And I should see a "div#flash-message" element
      And I should see text matching "Notice: Deleting Classification Beetroot"
      And I should see text matching "Notice: Finished Deleting Classification"

Scenario: Classification Delete Button Exists
    Given I am on "/classification/" 
     Then I should see a "table#classification-list" element
      And I should see a "Classification delete button for Beetroot" element
      And I should see a "Classification delete button for Onions" element
