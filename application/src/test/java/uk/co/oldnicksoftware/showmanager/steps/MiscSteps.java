/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;

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
    
}
