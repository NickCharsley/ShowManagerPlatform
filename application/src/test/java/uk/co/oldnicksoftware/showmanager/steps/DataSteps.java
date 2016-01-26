/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import static java.lang.Thread.sleep;
import java.util.Random;
import uk.co.oldnicksoftware.showmanager.helpers.*;

/**
 *
 * @author nick
 */
public class DataSteps {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }    
    //Helpers
    private final FileToolBarHelper fileToolBarHelper;
    private final ConfirmObjectSaveHelper confirmObjectSaveHelper;
    private final ObjectNotSavableHelper objectNotSavableHelper;
    private final CollectionHelper collectionHelper;    

    public DataSteps(FileToolBarHelper fileToolBarHelper
                    ,ConfirmObjectSaveHelper confirmObjectSaveHelper
                    ,ObjectNotSavableHelper objectNotSavableHelper
                    ,CollectionHelper collectionHelper
                    ){
        this.fileToolBarHelper=fileToolBarHelper;
        this.confirmObjectSaveHelper=confirmObjectSaveHelper;
        this.objectNotSavableHelper=objectNotSavableHelper;
        this.collectionHelper=collectionHelper;        
    }
    
    @Then("^I smell the roses for (\\d+) seconds$")
    public void I_smell_the_roses_for_seconds(int seconds) throws Throwable {
        sleep(seconds*1000);
    }
    
    @Given("^I have an Exhibition \"([^\"]*)\"$")
    public void createExhibition(String exhibition) throws Throwable {
        collectionHelper.createExhibition(exhibition);        
    }       
    
    @Given("^I Make Exhibition \"([^\"]*)\" (?:the |)Default$")
    public void makeExhibitionDefault(String name) throws Throwable {        
        collectionHelper.makeExhibitionDefault(name);
    }
    
    @Given("^(?:I|i) refresh the Exhibition List$")
    public void refreshExhibitionList() throws Throwable {
        collectionHelper.reloadPanel("ExhibitionList Window");
    }

    @When("^I save the edits$")
    public void saveEdits() throws Throwable {
        fileToolBarHelper.clickSave();
        confirmObjectSaveHelper.pressOkButton();
    }

    @Then("^I cannot save the edits$")
    public void cannotSaveEdits() throws Throwable {
        fileToolBarHelper.clickSave();
        objectNotSavableHelper.assertIsDisplayed();
        objectNotSavableHelper.pressOkButton();
    }    
    
    @Given("^I have an Exhibition \"([^\"]*)\" Section \"([^\"]*)\" Named \"([^\"]*)\"$")
    public void exhibitionSectionNamed(String exhibition, String sectionNumber, String section) throws Throwable {        
        collectionHelper.createExhibitionSection(exhibition,section,sectionNumber);
    }
    
    @Given("^I have (an Empty|an empty|the test|Full) Database$")
    public void haveDatabase(String databaseType) throws Throwable {
        collectionHelper.haveDatabase(databaseType);
    }            
}
