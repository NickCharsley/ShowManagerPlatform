/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.PendingException;
import uk.co.oldnicksoftware.showmanager.helpers.*;

/**
 *
 * @author nick
 */
public class ExhibitionSteps {
    private final NewExhibitionWizardHelper newExhibitionWizardHelper;
    private final ExhibitionListHelper exhibitionListHelper;
    private final ExhibitionEditorHelper exhibitionEditorHelper;
    
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }

    public ExhibitionSteps(NewExhibitionWizardHelper newExhibitionWizardHelper
                            ,ExhibitionListHelper exhibitionListHelper
                            ,ExhibitionEditorHelper exhibitionEditorHelper){
        this.newExhibitionWizardHelper=newExhibitionWizardHelper;
        this.exhibitionListHelper=exhibitionListHelper;
        this.exhibitionEditorHelper=exhibitionEditorHelper;
    }
    
    @Given("^(?:I|i) Display the New Exhibition Dialog$")
    public void displayNewExhibitionDialog() throws Throwable {
        newExhibitionWizardHelper.display();
    }

    @When("^(?:I|i) Cancel the New Exhibition Dialog$")
    public void cancelNewExhibitionDialog() throws Throwable {
        newExhibitionWizardHelper.cancel();
    }

    @Then("^(?:T|t)he New Exhibition Dialog is hidden$")
    public void newExhibitionDialogHidden() throws Throwable {
        newExhibitionWizardHelper.assertIsHidden();
    }

    @Then("^(?:T|t)he New Exhibition Dialog is displayed$")
    public void newExhibitionDialogDisplayed() throws Throwable {
        newExhibitionWizardHelper.assertIsDisplayed();
    }
  
    @Given("^(?:I|i) set the New Exhibition(?:s|'s) Name to \"([^\"]*)\"$")
    public void setNewExhibitionName(String name) throws Throwable {
        newExhibitionWizardHelper.setName(name);
    }

    @Given("^(?:I|i) submit the New Exhibition Dialog$")
    public void submitNewExhibition() throws Throwable {
        newExhibitionWizardHelper.finish();
    }

    @Given("^(?:I|i) set the New Exhibition to Default$")
    public void setNewExhibitionDefault() throws Throwable {
        newExhibitionWizardHelper.setDefault(true);
    }        

    @Given("^(?:I|i) request Exhibition \"([^\"]*)\" be deleted$")
    public void requestExhibitionDeletion(String name) throws Throwable {
        exhibitionListHelper.assertNodeInList(name,true);
        exhibitionListHelper.requestNodeDelete(name);
    }    

    @Then("^(?:T|t)he Exhibition List contains \"([^\"]*)\"$")
    public void exhibitionListContains(String name) throws Throwable {
        exhibitionListHelper.assertNodeInList(name,true);
    }    
    
    @Then("^(?:T|t)he Exhibition List dosn't contain \"([^\"]*)\"$")
    public void exhibitionListNotContain(String name) throws Throwable {
        exhibitionListHelper.assertNodeInList(name,false);
    }
    
    @Then("^(?:T|t)he New Exhibition Dialog displays the error \"([^\"]*)\"$")
    public void newExhibitionDialogError(String error) throws Throwable {
        newExhibitionWizardHelper.assertErrorContains(error);
    }        
    
    @Given("^(?:I|i) (?:can |)edit Exhibition \"([^\"]*)\"$")
    public void doEdit(String name) throws Throwable {
        exhibitionListHelper.assertNodeInList(name,true);
        exhibitionListHelper.requestNodeEdit(name);
        //Check it happened....
        exhibitionEditorHelper.assertNameIs(name);
    }

    @When("^(?:I|i) set the Exhibition(?:s|'s) name to \"([^\"]*)\"$")
    public void setExhibitionName(String name) throws Throwable {
        exhibitionEditorHelper.setName(name);
    }
    
    @When("^(?:I|i) make the Exhibition (the|no longer) Default$")
    public void makeExhibitionDefault(String type) throws Throwable {
        exhibitionEditorHelper.setDefault(type.equals("the"));
    }

    @Then("^(?:T|t)he Exhibition (is|is not) the Default$")
    public void exhibitionIsDefault(String type) throws Throwable {
        exhibitionEditorHelper.assertDefaultIs(type.equals("is"));    
    }
    
    @Then("^the Exhibition(?:s|'s) name is \"([^\"]*)\"$")
    public void exhibitionName(String name) throws Throwable {
        exhibitionEditorHelper.assertNameIs(name);
    }
}
