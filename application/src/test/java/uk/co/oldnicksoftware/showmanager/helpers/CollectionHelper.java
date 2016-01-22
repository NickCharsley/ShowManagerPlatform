/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.openide.util.Lookup;
import uk.co.oldnicksoftware.showmanager.api.entities.*;
import uk.co.oldnicksoftware.showmanager.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.RemovableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.SaveableEntityCapability;
import uk.co.oldnicksoftware.showmanager.domain.*;

/**
 *
 * @author nick
 */
public class CollectionHelper {
    //Data Collections
    private final ExhibitionCollection exhibitionCollection;
    private final SectionCollection sectionCollection;
    private final ExhibitionSectionCollection exhibitionSectionCollection;
    private final DefaultsSingleton defaultsSingleton; 

    public CollectionHelper(){
        Lookup defaultLookup = Lookup.getDefault();
        
        exhibitionCollection        = defaultLookup.lookup(ExhibitionCollection.class);
        sectionCollection           = defaultLookup.lookup(SectionCollection.class);
        exhibitionSectionCollection = defaultLookup.lookup(ExhibitionSectionCollection.class);
        defaultsSingleton           = defaultLookup.lookup(DefaultsSingleton.class);
        
    }
    
    public Exhibition createExhibition(String name) throws Exception{
        assertThat("Have Exhibition Collection",exhibitionCollection,is(notNullValue()));

        Exhibition e=exhibitionCollection.getExhibition(new Exhibition(name));        

        CreatableEntityCapability cec = exhibitionCollection.getLookup().lookup(CreatableEntityCapability.class);
        assertThat("Exhibition Creatable",cec,is(notNullValue()));
        cec.create(e);                
        
        return e;
    }

    public Section createSection(String name) throws Exception{
        assertThat("Have Section Collection",sectionCollection,is(notNullValue()));

        Section s= sectionCollection.getSection(new Section(name));
        
        CreatableEntityCapability cec = sectionCollection.getLookup().lookup(CreatableEntityCapability.class);
        assertThat("Section Creatable",cec,is(notNullValue()));
        cec.create(s);                        
        
        return s;
    }

    public ExhibitionSection createExhibitionSection(Exhibition exhibition,Section section,String name) throws Exception{
        assertThat("Have Exhibition Section Collection",exhibitionSectionCollection,is(notNullValue()));

        ExhibitionSection es=exhibitionSectionCollection.getExhibitionSection(new ExhibitionSection(exhibition,section,name));
        
        CreatableEntityCapability cec = exhibitionSectionCollection.getLookup().lookup(CreatableEntityCapability.class);
        assertThat("Exhibition Section Creatable",cec,is(notNullValue()));
        cec.create(es);                        
        
        return es;
    }
    
    
    public void makeExhibitionDefault(String name) throws Throwable {        
        assertThat("Have Defaults Singleton",defaultsSingleton,is(notNullValue()));
        assertThat("Have Exhibition Collection",exhibitionCollection,is(notNullValue()));
        
        Exhibition e= new Exhibition();
        e.setName(name);
        e=exhibitionCollection.getExhibition(e);        
        
        Defaults d=defaultsSingleton.getDefaults();
        
        d.link(e);        
        
        SaveableEntityCapability secDefaults= defaultsSingleton.getLookup().lookup(SaveableEntityCapability.class);
        secDefaults.save(d);
        SaveableEntityCapability secExhibition= exhibitionCollection.getLookup().lookup(SaveableEntityCapability.class);
        secExhibition.save(e);        
    }

    public void reloadPanel(String panel) throws Throwable {
        //Get Explorer Window and Perform refresh...
        if (panel.equalsIgnoreCase("ExhibitionList Window")){
            assertThat("Have Exhibition Collection",exhibitionCollection,is(notNullValue()));
            exhibitionCollection.reload();
        }
    }    
    
    
    private void buildExhibitions(String databaseType){
    }
    
    private void emptyCollection(EntityCollection entity) throws Exception{
        RemovableEntityCapability rec=entity.getLookup().lookup(RemovableEntityCapability.class);
        if (rec==null) return;
        rec.removeAll();        
    }
    
    public void haveDatabase(String databaseType) throws Throwable {
        assertThat("Have Exhibition Collection",exhibitionCollection,is(notNullValue()));
        assertThat("Have Section Collection",sectionCollection,is(notNullValue()));
        assertThat("Have ExhibitionSection Collection",exhibitionSectionCollection,is(notNullValue()));
        assertThat("Have Defaults Singleton",defaultsSingleton,is(notNullValue()));
        
        try {
            //Always empty first
            //Need to perform in correct order...            
            emptyCollection(defaultsSingleton);
            emptyCollection(exhibitionSectionCollection);
            emptyCollection(sectionCollection);
            emptyCollection(exhibitionCollection);
            
            if (!databaseType.equalsIgnoreCase("an empty")){            
                buildExhibitions(databaseType);
  //              buildPurchaseOrders();
            }        
        } finally {            
            exhibitionCollection.reload();
            sectionCollection.reload();
            exhibitionCollection.reload();
            defaultsSingleton.reload();
        }
    }        
        
}
