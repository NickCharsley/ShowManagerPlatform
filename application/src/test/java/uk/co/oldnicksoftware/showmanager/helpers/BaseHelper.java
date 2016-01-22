/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import cucumber.api.jelly.Helper;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author nick
 */
abstract class BaseHelper<E> {
    protected final String targetName;
    
    protected BaseHelper(String targetName){
        this.targetName=targetName;
    }

    abstract protected E operator();      
    
    private void dialogVisability(String dialog,boolean shown){        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,Helper.dialogVisable(dialog,shown)
                ,is(shown));
    }
        
    public void assertIsDisplayed(){
        dialogVisability(targetName,true);
    }

    public void assertIsHidden(){
        dialogVisability(targetName,false);        
    }    
}
