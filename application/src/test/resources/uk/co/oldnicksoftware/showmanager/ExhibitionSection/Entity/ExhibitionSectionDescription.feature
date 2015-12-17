Feature: ExhibitionSections Have Descriptions
    In order to manage ExhibitionSections
    As a Developer
    I need a Description to call the ExhibitionSection

Background:
    Given I have an Empty Database
      And I have an Exhibition "Spring Show 2011" Section "Vegtables" Named "1" 

Scenario: ExhibitionSections have Description
    Given I Describe ExhibitionSection "Spring Show 2011':'1" as "Description"  
     Then ExhibitionSection "Spring Show 2011':'1" "Description" is writableable
      And ExhibitionSection "Spring Show 2011':'1" "Description" is readable    
      And ExhibitionSection "Spring Show 2011':'1" is Described as "Description"

Scenario: ExhibitionSections Description are not required
    Given ExhibitionSection "Spring Show 2011':'1" is Described as ""
     Then ExhibitionSection "Spring Show 2011':'1" is valid

Scenario: ExhibitionSection Description are not unique
    Given I Describe ExhibitionSection "Spring Show 2011':'1" as "Description"
      And I have an Exhibition "Spring Show 2011" Section "Flowers" Named "2" 
      And I save everything
     Then I can Describe ExhibitionSection "Spring Show 2011':'2" as "Description"
      And ExhibitionSection "Spring Show 2011':'2" is valid
     