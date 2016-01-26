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
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

/**
 *
 * @author nick
 */
@ServiceProvider(service = ExhibitionCollection.class)
public class ExhibitionMemoryCollection extends MemoryCollection<Exhibition> implements ExhibitionCollection<Exhibition>{
    private Map<Integer,Exhibition> searchExhibitionsByID;
    private Map<String,Exhibition> searchExhibitionsByName;

    private void buildIndexes(){
        //Clear All Indexes
        searchExhibitionsByName.clear();
        searchExhibitionsByID.clear();                                
        //Rebuild them...
        for (Iterator it = collection.iterator(); it.hasNext();) {
            Exhibition exhibition = (Exhibition)it.next();
            searchExhibitionsByID.put(exhibition.getId(),exhibition);
            searchExhibitionsByName.put(exhibition.getName(), exhibition);           
        }        
    }

    public ExhibitionMemoryCollection(){
        searchExhibitionsByID=new HashMap();
        searchExhibitionsByName=new HashMap();
        // Add a "Reloadable" ability to this entity:
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<Exhibition>() {
            @Override
            public void create(Exhibition exhibition) throws Exception {
                //if id is not set we set it...
                if(exhibition.getId() == null){
                    //This is Fake so we just generate a good random number..
                    Random generator = new Random();                
                    exhibition.setId(generator.nextInt());                    
                }                
                if (!searchExhibitionsByID.containsKey(exhibition.getId())){
                    getExhibitions().add(exhibition);
                    buildIndexes();
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<Exhibition>() {
            @Override
            public void remove(Exhibition exhibition) throws Exception {
                if (searchExhibitionsByID.containsKey(exhibition.getId())){
                    getExhibitions().remove(exhibition);
                    exhibition.unlink();
                    buildIndexes();
                }
            }            

            @Override
            public void removeAll() throws Exception {
                for (Iterator it = getExhibitions().iterator(); it.hasNext();) {
                    Exhibition exhibition = (Exhibition)it.next();
                    exhibition.unlink();
                }           
                collection.clear();
                searchExhibitionsByID.clear();
                searchExhibitionsByName.clear();
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<Exhibition>() {
            @Override
            public void save(Exhibition exhibition) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }

            @Override
            public boolean isSavable(Exhibition entity) {
                return isAddable(entity);                 
            }
        });        
    }
    
    @Override
    public List getExhibitions() {
        return collection;
    }

    @Override
    public Exhibition getExhibition(Exhibition search) {
        if (searchExhibitionsByID.containsKey(search.getId())){
            return searchExhibitionsByID.get(search.getId());
        }
        if (searchExhibitionsByName.containsKey(search.getName())){
            return searchExhibitionsByName.get(search.getName());
        }
        return search;
    }

    @Override
    public boolean isAddable(Exhibition exhibition) {
        if (!searchExhibitionsByName.containsKey(exhibition.getName())) return true;
        return (Objects.equals(searchExhibitionsByName.get(exhibition.getName()).getId(), exhibition.getId()));        
    }

}
