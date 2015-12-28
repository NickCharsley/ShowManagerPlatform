@tbc
Feature: Create an Classification with a New Name
    In order to manage classifications
    As a User
    I need to be able to create an classification

Background:
    Given I have an Empty Database 

Scenario: New Classification Button Exists
    Given I am on "/classification/" 
     Then I should see a "a#new_classification" element

Scenario: New Classification Form Exists
    Given I am on "/classification/new" 
     Then I should see a "div#ons_showmanagerbundle_classification" element

Scenario: Create a Classification
    Given I am on "/classification/new" 
     Then I have 0 Records in the "ONSShowManagerBundle Classification" repository
     When I fill in "Name" with "New Classification"
      And I press "ons_showmanagerbundle_classification_submit"
     Then I should be on "Classification classification page for New Classification"
      And I should see a "table#classification" element
      And I should see "New Classification" in the "#classification" element
     When I follow "back"
     Then I should be on "/classification/"

Scenario: Classification Names are Unique
    Given I have a Classification "Spring Classification 2011"                            
      And I save everything                                                    
     Then I have 1 Record in the "ONSShowManagerBundle Classification" repository
     When I am on "/classification/new" 
      And I fill in "Name" with "Spring Classification 2011"
      And I press "ons_showmanagerbundle_classification_submit"
     Then I should be on "/classification/"
      And I should see a "div#ons_showmanagerbundle_classification" element
      And I should see "This value is already used." in the "#ons_showmanagerbundle_classification" element
