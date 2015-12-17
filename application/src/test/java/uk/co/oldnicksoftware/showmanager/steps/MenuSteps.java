/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import org.netbeans.jellytools.actions.ActionNoBlock;

/**
 *
 * @author nick
 */
public class MenuSteps {
    @When("^I click the \"([^\"]*)\" menu$")
    public void clickMenu(String menu) throws Throwable {
        new ActionNoBlock(menu, null).performMenu();
    }    
}
