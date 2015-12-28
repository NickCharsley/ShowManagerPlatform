@tbc
Feature: Edit Classification
    In order to manage Classifications
    As a User
    I need to be able to rename an Classification

Background:
    Given I have an Empty Database 
      And I have a Classification "Beetroot"                            
      And I have a Classification "Onions"   
      And I save everything                                                    
      And I am on "/classification/" 

Scenario: Classification Edit Button Exists
    Given I should see a "table#classification-list" element
      And I should see "Beetroot" in the "#classification-list" element
      And I should see a "Classification edit button for Beetroot" element
      And I should see "Onions" in the "#classification-list" element
      And I should see a "Classification edit button for Onions" element
    
Scenario: Edit a Classification
    Given I am on "/classification/"
     Then I should see a "Classification edit button for Beetroot" element
     When I follow "Classification edit link for Beetroot"
     Then I should be on "Classification classification edit page for Beetroot"
      And I should see a "div#ons_showmanagerbundle_classification" element
      And the "ons_showmanagerbundle_classification_name" field should contain "Beetroot"
     When I fill in "ons_showmanagerbundle_classification_name" with "Updated Beetroot"
      And I press "ons_showmanagerbundle_classification_submit"
     Then I should be on "Classification classification edit page for Updated Beetroot"
      And I should see a "div#ons_showmanagerbundle_classification" element
      And the "ons_showmanagerbundle_classification_name" field should contain "Updated Beetroot"
     When I follow "back"
     Then I should be on "/classification/"
      And I should see a "table#classification-list" element
      And I should see "Updated Beetroot" in the "#classification-list" element

Scenario: Cannot Edit a Classification to have the same Name as another Classification
    Given I am on "/classification/"
     Then I should see a "Classification edit button for Beetroot" element
     When I follow "Classification edit link for Beetroot"
     Then I should be on "Classification classification edit page for Beetroot"
      And I should see a "div#ons_showmanagerbundle_classification" element
      And the "ons_showmanagerbundle_classification_name" field should contain "Beetroot"
     When I fill in "ons_showmanagerbundle_classification_name" with "Onions"
      And I press "ons_showmanagerbundle_classification_submit"
     Then I should be on "Classification classification page for Beetroot"
      And I should see a "div#ons_showmanagerbundle_classification" element
      And the "ons_showmanagerbundle_classification_name" field should contain "Onions"
      And I should see "This value is already used." in the "#ons_showmanagerbundle_classification" element
