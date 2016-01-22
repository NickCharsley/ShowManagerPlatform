@tbc
Feature: ExhibitionSectionClassification List Page
    In order to manage ExhibitionSectionClassifications
    As a User
    I need a Page detailing the Current ExhibitionSectionClassifications

Background:
    Given I have an Empty Database

Scenario: There are No ExhibitionSectionClassifications
    Given I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "No Default Show is set." in the "#exhibitionsectionclassification-list" element

Scenario: There is No Default Exhibition
    Given I have an Exhibition "Spring Show 2011"
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "No Default Show is set." in the "#exhibitionsectionclassification-list" element

Scenario: There are No ExhibitionSections
    Given I have an Exhibition "Spring Show 2011"
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "No Show Sections Exist." in the "#exhibitionsectionclassification-list" element

Scenario: There are No ExhibitionSectionClassifications
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification-list" element
      And I should see "No Section Classifications Exist." in the "#exhibitionsectionclassification-list" element

Scenario: There are No ExhibitionSectionClassifications in a Section
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Beetroot" Named "B"  
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification-list" element
      And I should see "Flowers" in the "#exhibitionsectionclassification-list" element
      And I should see "Beetroot" in the "#exhibitionsectionclassification-list" element
      And I should see "No Section Classifications Exist." in the "#exhibitionsectionclassification-list" element

Scenario: There is One ExhibitionSectionClassification
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I Make Exhibition "Spring Show 2011" Default
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Beetroot" Named "B"  
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification-list" element
      And I should see "Beetroot" in the "#exhibitionsectionclassification-list" element

Scenario: There are Many ExhibitionSectionClassifications
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Onion" Named "A"  
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Beetroot" Named "B"  
      And I have an ExhibitionSection "Spring Show 2011':'2" Classification "Rose" Named "C"  
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsectionclassification/"
     Then I should see a "table#exhibitionsectionclassification-list" element
      And I should see "Vegtables" in the "#exhibitionsectionclassification-list" element
      And I should see "Flowers" in the "#exhibitionsectionclassification-list" element
      And I should see "Beetroot" in the "#exhibitionsectionclassification-list" element
      And I should see "Onion" in the "#exhibitionsectionclassification-list" element
      And I should see "Rose" in the "#exhibitionsectionclassification-list" element
      And I should not see "No Section Classifications Exist." in the "#exhibitionsectionclassification-list" element

Scenario: Buttons exist to View, Edit and Delete an ExhibitionSectionClassification
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an ExhibitionSection "Spring Show 2011':'1" Classification "Onion" Named "A"  
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsectionclassification/"
     Then I should see an "ExhibitionSectionClassification show button for 'Spring Show 2011':'1':'A'" element
      And I should see an "ExhibitionSectionClassification edit button for 'Spring Show 2011':'1':'A'" element
      And I should see an "ExhibitionSectionClassification delete button for 'Spring Show 2011':'1':'A'" element

Scenario: Buttons exist to add New ExhibitionSectionClassification
    Given I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2"
      And I Make Exhibition "Spring Show 2011" Default
     When I am on "/exhibitionsectionclassification/"
     Then I should see an "ExhibitionSection new button for 'Spring Show 2011':'1'" element
      And I should see an "ExhibitionSection new button for 'Spring Show 2011':'2'" element
