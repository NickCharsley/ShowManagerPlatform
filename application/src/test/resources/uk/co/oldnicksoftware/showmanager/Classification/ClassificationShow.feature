Feature: Show Classification
    In order to manage Classifications
    As a User
    I need to be able to view an Classification

Background:
    Given I have an Empty Database 
      And I have a Classification "Beetroot"                            
      And I have a Classification "Onions"                              
      And I save everything                                                    

Scenario: Classification classification Button Exists
    Given I am on "/classification/" 
     Then I should see a "table#classification-list" element
      And I should see "Beetroot" in the "#classification-list" element
      And I should see a "Classification classification button for Beetroot" element
      And I should see "Onions" in the "#classification-list" element
      And I should see a "Classification classification button for Onions" element
    
Scenario: Show an Empty Classification
    Given I am on "/classification/" 
     Then I should see a "Classification classification button for Beetroot" element
     When I follow "Classification classification link for Beetroot"
     Then I should be on "Classification classification page for Beetroot"
      And I should see a "table#classification" element
      And I should see "Beetroot" in the "#classification" element
     When I follow "back"
     Then I should be on "/classification/"

Scenario: Show a Full Classification
    Given I am on "/classification/" 
     Then I should see a "Classification classification button for Beetroot" element
     When I follow "Classification classification link for Beetroot"
     Then I should be on "Classification classification page for Beetroot"
      And I should see a "table#classification" element
      And I should see "Beetroot" in the "#classification" element
     When I follow "back"
     Then I should be on "/classification/"

