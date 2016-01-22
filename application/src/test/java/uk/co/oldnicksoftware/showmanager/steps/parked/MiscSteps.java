/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps.parked;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JTreeOperator;
import org.openide.explorer.ExplorerManager;

/**
 *
 * @author nick
 */
public class MiscSteps {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }
    @Given("^I have an? \"([^\"]*)\" Application$")
    public void haveApplication(String application) throws Throwable {
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        assertThat("MainWindowOperator is a Netbeans Main Window",mwo.getName(),is("NbMainWindow"));
        //Looks like Cucumber Jelly Fails to load Branding !!! Must Fix
        //assertThat("Application is Named",mwo.getTitle(),is(application));
    }
        
    @Then("^I have an? \"([^\"]*)\" Panel$")
    public void haveAPanel(String panel) throws Throwable {
        assertThat(new TopComponentOperator(panel),is(notNullValue()));        
    }    
         
    @Given("^I have the following panels:$")
    public void havePanels(DataTable dtPanels) throws Throwable {
        for (Map<String, String> map : dtPanels.asMaps()) {
            haveAPanel(map.get("panel"));
        }
    }   
}
