/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api.capabilities;

/**
 *
 * @author nick
 * @param <Entity>
 */
public interface SaveableEntityCapability<Entity> {
    public void save(Entity entity) throws Exception;    
    public boolean isSavable(Entity entity);    
}
