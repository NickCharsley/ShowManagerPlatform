/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;

import cucumber.runtime.PendingException;
import uk.co.oldnicksoftware.showmanager.helpers.ConfirmObjectDeletionHelper;

/**
 *
 * @author nick
 */
public class DeletionSteps {
    private final ConfirmObjectDeletionHelper confirmObjectDeletionHelper;
    
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }
    
    public DeletionSteps(ConfirmObjectDeletionHelper confirmObjectDeletionHelper){
        this.confirmObjectDeletionHelper=confirmObjectDeletionHelper;
    }

    @Then("^(?:I|i) should be asked to confirm the deletion$")
    public void askedConfirmDeletion() throws Throwable {
       confirmObjectDeletionHelper.assertIsDisplayed();
    }

    @When("^(?:I|i) (don't |)confirm the deletion$")
    public void confirmDeletion(String type) throws Throwable {
        if (type.equals("don't ")){
            confirmObjectDeletionHelper.pressNoButton();
        }
        else
        {
            confirmObjectDeletionHelper.pressYesButton();
        }
    }
}
