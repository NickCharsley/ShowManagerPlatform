/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.entityservice.memory.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.api.entities.SectionCollection;
import uk.co.oldnicksoftware.showmanager.domain.ExhibitionSection;
import uk.co.oldnicksoftware.showmanager.domain.Section;

/**
 *
 * @author nick
 */
@ServiceProvider(service = SectionCollection.class)
public class SectionMemoryCollection extends MemoryCollection<Section> implements SectionCollection {
    private Map<Integer,Section> searchSectionsByID;
    private Map<String,Section> searchSectionsByName;

    private void buildIndexes(){
        //Clear All Indexes
        searchSectionsByName.clear();
        searchSectionsByID.clear();                                
        //Rebuild them...
        for (Iterator it = collection.iterator(); it.hasNext();) {
            Section section = (Section)it.next();
            searchSectionsByID.put(section.getId(),section);
            searchSectionsByName.put(section.getName(), section);           
        }        
    }

    public SectionMemoryCollection(){
        searchSectionsByID=new HashMap();
        searchSectionsByName=new HashMap();
        // Add a "Reloadable" ability to this entity:
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<Section>() {
            @Override
            public void create(Section section) throws Exception {
                //if id is not set we set it...
                if(section.getId() == null){
                    //This is Fake so we just generate a good random number..
                    Random generator = new Random();                
                    section.setId(generator.nextInt());                    
                }                
                if (!searchSectionsByID.containsKey(section.getId())){
                    getSections().add(section);
                    buildIndexes();
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<Section>() {
            @Override
            public void remove(Section section) throws Exception {
                if (searchSectionsByID.containsKey(section.getId())){
                    getSections().remove(section);
                    section.unlink();
                    buildIndexes();
                }
            }            

            @Override
            public void removeAll() throws Exception {
                for (Iterator it = getSections().iterator(); it.hasNext();) {
                    Section section = (Section)it.next();
                    section.unlink();
                }           
                collection.clear();
                searchSectionsByID.clear();
                searchSectionsByName.clear();
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<Section>() {
            @Override
            public void save(Section section) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }

            @Override
            public boolean isSavable(Section entity) {
                return isAddable(entity);                 
            }
        });        
    }
    
    @Override
    public List getSections() {
        return collection;
    }

    @Override
    public Section getSection(Section search) {
        if (searchSectionsByID.containsKey(search.getId())){
            return searchSectionsByID.get(search.getId());
        }
        if (searchSectionsByName.containsKey(search.getName())){
            return searchSectionsByName.get(search.getName());
        }
        return search;
    }

    @Override
    public boolean isAddable(Section section) {
        if (!searchSectionsByName.containsKey(section.getName())) return true;
        return (Objects.equals(searchSectionsByName.get(section.getName()).getId(), section.getId()));        
    }

}
