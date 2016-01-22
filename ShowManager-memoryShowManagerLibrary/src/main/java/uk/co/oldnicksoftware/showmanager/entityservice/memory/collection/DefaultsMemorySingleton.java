/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.entityservice.memory.collection;

import java.util.Objects;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.showmanager.api.entities.DefaultsSingleton;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.domain.Defaults;

/**
 *
 * @author nick
 */
@ServiceProvider(service = DefaultsSingleton.class)
public class DefaultsMemorySingleton extends MemorySingleton implements DefaultsSingleton {
    private Defaults defaults=new Defaults(1);
    
    public DefaultsMemorySingleton(){
        /* Add a "Reloadable" ability to this entity: */
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        /* ...and a "Creatable" ability: Not Creatable?* /
        instanceContent.add(new CreatableEntityCapability<Defaults>() {
            @Override
            public void create(Defaults defaults) throws Exception {
                //if id is not set we set it...
                if(defaults.getId() == null){
                    //This is Fake so we just generate a good random number..
                    Random generator = new Random();                
                    defaults.setId(generator.nextInt());                    
                }                
                if (!searchDefaultssByID.containsKey(defaults.getId())){
                    getDefaultss().add(defaults);
                    searchDefaultssByID.put(defaults.getId(),defaults);
                    searchDefaultssByName.put(defaults.getName(), defaults);
                }
            }
        });
        /* ...and a "Removeable" ability:*/
        instanceContent.add(new RemovableEntityCapability<Defaults>() {
            @Override
            public void remove(Defaults defaults) throws Exception {
                /**
                 * Remove actually just 'Cleans' the data
                 */
                defaults.link(null);
                defaults.setShowName("");
            }            
            @Override
            public void removeAll() throws Exception {
                remove(defaults);
            }
        });
        /* ... and a "Savable" ability:*/
        instanceContent.add(new SaveableEntityCapability<Defaults>() {
            @Override
            public void save(Defaults defaults) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }

            @Override
            public boolean isSavable(Defaults newDefaults) {
                 return (Objects.equals(defaults.getId(), newDefaults.getId()));  
            }
        });                
    }    
    
    @Override
    public Defaults getDefaults() {
        return defaults;
    }
}
