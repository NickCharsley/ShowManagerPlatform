/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import cucumber.api.jelly.helpers.*;
import cucumber.runtime.PendingException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author nick
 */
public class ShowManagerDialogs {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }

    @Then("^(?:T|t)he \"([^\"]*)\" Dialog is (displayed|hidden)$")
    public void dialogVisability(String dialog,String displayed){
        boolean shown=displayed.equals("displayed");
        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,NbDialog.isVisible(dialog,shown)                
                ,is(shown));
    }
    
    @When("^I click the \"([^\"]*)\" (ok|close|cancel|yes|no) button$")
    public void clickTheButton(String dialog,String button){
        NbDialog.clickButton(dialog,button);
    }    
    
}
