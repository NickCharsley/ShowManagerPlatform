/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps.parked;

import cucumber.api.java.en.*;
import cucumber.api.jelly.Helper;

import cucumber.runtime.PendingException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.NbDialogOperator;

/**
 *
 * @author nick
 */
public class DialogSteps {
    
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }

    @Then("^(?:T|t)he \"([^\"]*)\" Dialog is (displayed|hidden)$")
    public void dialogVisability(String dialog,String displayed) throws Throwable {
        boolean shown=displayed.equals("displayed");
        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,Helper.dialogVisable(dialog,shown)
                ,is(shown));
    }
    
    @When("^(?:I|i) click the \"([^\"]*)\" (ok|close|cancel|yes|no) button$")
    public void clickTheButton(String dialog,String button) throws Throwable {
        NbDialogOperator doD=new NbDialogOperator(dialog); 
        switch (button.toLowerCase()) {
            case "ok":               
                doD.ok();
                break;
            case "close":
                doD.closeByButton();
                break;
            case "cancel":
                doD.cancel();                
                break;
            case "yes":               
                doD.yes();
                break;
            case "no":               
                doD.no();
                break;
        }
    }    
        
}
