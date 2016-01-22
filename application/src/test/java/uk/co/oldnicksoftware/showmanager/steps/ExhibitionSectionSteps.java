/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import static java.lang.Thread.sleep;
import uk.co.oldnicksoftware.showmanager.helpers.ExhibitionListHelper;

/**
 *
 * @author nick
 */
public class ExhibitionSectionSteps {
    private final ExhibitionListHelper exhibitionListHelper;
   
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }    

    public ExhibitionSectionSteps(
                        ExhibitionListHelper exhibitionListHelper
                    ){
        this.exhibitionListHelper=exhibitionListHelper;
    }
    
    @Then("^The Exhibition List item \"([^\"]*)\" is (not |)expandable$")
    public void exhibitionItemExpandable(String name,String type) throws Throwable {
        exhibitionListHelper.assertNodeInList(name,true);
        exhibitionListHelper.assertNodeExpandable(name,type.equals(""));  
    }
    
    @Then("^The Exhibition List item \"([^\"]*)\" has (a|no) sub item \"([^\"]*)\"$")
    public void exhibitionItemSubItem(String name, String type,String subName) throws Throwable {
        //if (type.equals("a")) sleep(5000);
        exhibitionListHelper.assertNodeInList(name,subName,type.equals("a"));
    }
}
