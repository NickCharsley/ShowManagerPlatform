/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.steps;

import cucumber.api.java.en.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;
import uk.co.oldnicksoftware.showmanager.api.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.RemovableEntityCapability;

/**
 *
 * @author nick
 */
public class DataSteps {
    private final ExhibitionCollection exhibitionCollection;

    public DataSteps(){
        Lookup defaultLookup = Lookup.getDefault();
        
        exhibitionCollection      = defaultLookup.lookup(ExhibitionCollection.class);
    }

    private void buildExhibitions(String databaseType){
        
    }
    
    @Given("^I have (an Empty|an empty|the test) Database$")
    public void haveDatabase(String databaseType) throws Throwable {
        assertThat("Have Exhibition Collection",exhibitionCollection,is(notNullValue()));
        //assertThat("Have Purchase Order Collection",purchaseOrderCollection,is(notNullValue()));
        
        try {
            //Always empty first
            //Need to perform in correct order...            
//            RemovableEntityCapability recPurchaseOrder= purchaseOrderCollection.getLookup().lookup(RemovableEntityCapability.class);
//            recPurchaseOrder.removeAll();
            RemovableEntityCapability recExhibition= exhibitionCollection.getLookup().lookup(RemovableEntityCapability.class);
            recExhibition.removeAll();
            
            if (!databaseType.equalsIgnoreCase("an empty")){            
                buildExhibitions(databaseType);
  //              buildPurchaseOrders();
            }        
        } finally {            
            //Get Explorer Window and Perform refresh...
//            ExhibitionListTopComponent cltc=(ExhibitionListTopComponent)WindowManager
//                                            .getDefault()
//                                            .findTopComponent("ExhibitionListTopComponent");            
//            exhibitionCollection.reload(cltc.getExplorerManager().getRootContext());
        }
    }        
    
}
