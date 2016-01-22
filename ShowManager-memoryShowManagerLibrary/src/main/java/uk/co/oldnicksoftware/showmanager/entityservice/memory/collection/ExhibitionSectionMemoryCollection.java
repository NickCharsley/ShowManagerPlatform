/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.entityservice.memory.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionSectionCollection;
import uk.co.oldnicksoftware.showmanager.domain.ExhibitionSection;

/**
 *
 * @author nick
 */
@ServiceProvider(service = ExhibitionSectionCollection.class)
public class ExhibitionSectionMemoryCollection extends MemoryCollection<ExhibitionSection> implements ExhibitionSectionCollection {
    private Map<Integer,ExhibitionSection> searchExhibitionSectionsByID;
    private Map<String,ExhibitionSection> searchExhibitionSectionsByName;

    private String nameKey(ExhibitionSection exhibitionSection){
        return exhibitionSection.getExhibitionID().getName().concat(":->").concat(exhibitionSection.getSectionNumber());
    }    
    
    public ExhibitionSectionMemoryCollection(){
        searchExhibitionSectionsByID=new HashMap();
        searchExhibitionSectionsByName=new HashMap();
        // Add a "Reloadable" ability to this entity:
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<ExhibitionSection>() {
            @Override
            public void create(ExhibitionSection exhibitionSection) throws Exception {
                //if id is not set we set it...
                if(exhibitionSection.getId() == null){
                    //This is Fake so we just generate a good random number..
                    Random generator = new Random();                
                    exhibitionSection.setId(generator.nextInt());                    
                }                
                if (!searchExhibitionSectionsByID.containsKey(exhibitionSection.getId())){
                    getExhibitionSections().add(exhibitionSection);
                    searchExhibitionSectionsByID.put(exhibitionSection.getId(),exhibitionSection);
                    searchExhibitionSectionsByName.put(nameKey(exhibitionSection), exhibitionSection);
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<ExhibitionSection>() {
            @Override
            public void remove(ExhibitionSection exhibitionSection) throws Exception {
                if (searchExhibitionSectionsByID.containsKey(exhibitionSection.getId())){
                    getExhibitionSections().remove(exhibitionSection);
                    searchExhibitionSectionsByID.remove(exhibitionSection.getId());
                    searchExhibitionSectionsByName.remove(nameKey(exhibitionSection));
                }
            }            

            @Override
            public void removeAll() throws Exception {
                getExhibitionSections().clear();
                searchExhibitionSectionsByID.clear();
                searchExhibitionSectionsByName.clear();                
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<ExhibitionSection>() {
            @Override
            public void save(ExhibitionSection exhibitionSection) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }

            @Override
            public boolean isSavable(ExhibitionSection entity) {
                return isAddable(entity);                 
            }
        });        
    }
    
    @Override
    public List getExhibitionSections() {
        return collection;
    }

    @Override
    public ExhibitionSection getExhibitionSection(ExhibitionSection search) {
        if (searchExhibitionSectionsByID.containsKey(search.getId())){
            return searchExhibitionSectionsByID.get(search.getId());
        }
        if (searchExhibitionSectionsByName.containsKey(nameKey(search))){
            return searchExhibitionSectionsByName.get(nameKey(search));
        }
        return search;
    }

    @Override
    public boolean isAddable(ExhibitionSection exhibitionSection) {
        if (!searchExhibitionSectionsByName.containsKey(nameKey(exhibitionSection))) return true;
        return (Objects.equals(searchExhibitionSectionsByName.get(nameKey(exhibitionSection)).getId(), exhibitionSection.getId()));        
    }

}
