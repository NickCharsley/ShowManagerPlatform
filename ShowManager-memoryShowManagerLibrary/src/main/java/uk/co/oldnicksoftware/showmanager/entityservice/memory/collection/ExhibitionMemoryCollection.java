/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.entityservice.memory.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.showmanager.api.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.ReloadableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.RemovableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.SaveableEntityCapability;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

/**
 *
 * @author nick
 */
@ServiceProvider(service = ExhibitionCollection.class)
public class ExhibitionMemoryCollection extends MemoryCollection implements ExhibitionCollection {
    private Map<Integer,Exhibition> searchExhibitions;

    public ExhibitionMemoryCollection(){
        searchExhibitions=new HashMap();
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
                if (!searchExhibitions.containsKey(exhibition.getId())){
                    getExhibitions().add(exhibition);
                    searchExhibitions.put(exhibition.getId(),exhibition);
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<Exhibition>() {
            @Override
            public void remove(Exhibition exhibition) throws Exception {
                if (searchExhibitions.containsKey(exhibition.getId())){
                    getExhibitions().remove(exhibition);
                    searchExhibitions.remove(exhibition.getId());
                }
            }            

            @Override
            public void removeAll() throws Exception {
                getExhibitions().clear();
                searchExhibitions.clear();
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<Exhibition>() {
            @Override
            public void save(Exhibition exhibition) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }
        });        
    }
    
    @Override
    public List getExhibitions() {
        return collection;
    }

    @Override
    public Exhibition getExhibition(Exhibition search) {
        if (searchExhibitions.containsKey(search.getId())){
            return searchExhibitions.get(search.getId());
        }
        return search;
    }
}
