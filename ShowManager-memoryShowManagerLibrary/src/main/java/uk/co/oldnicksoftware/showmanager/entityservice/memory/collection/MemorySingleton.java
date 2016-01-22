/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.entityservice.memory.collection;

import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.showmanager.api.entities.EntityCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.ReloadableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.capabilities.ReloadableViewCapability;

/**
 *
 * @author nick
 */
public class MemorySingleton implements EntityCollection {
    protected final Lookup lookup;
    protected final InstanceContent instanceContent;
    protected final List<Node> rootNodes;

    public MemorySingleton(){
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        // Create a list of all root nodes
        rootNodes= new ArrayList<>();
    }    
    
    @Override
    public Lookup getLookup() {
        return lookup;
    }

    @Override
    public void reload(){
        try {
            //Refresh the list of collection via the implementation of the reload capability:
            ReloadableEntityCapability r = this.getLookup().lookup(ReloadableEntityCapability.class);
            if (r!=null) {
                r.reload();
            }
            for (Node rootNode : rootNodes) {
                ReloadableViewCapability rvc = rootNode.getLookup().lookup(ReloadableViewCapability.class);    
                if (rvc!=null) {
                    rvc.reloadChildren();
                }
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }       

    @Override
    public void addRootNode(Node rootNode) {
        rootNodes.add(rootNode);
                
    }

    @Override
    public void removeRootNode(Node rootNode) {
        rootNodes.remove(rootNode);
    }

    @Override
    public boolean isAddable(Object Entity) {
        return false;//Can never 'Add' another object
    }
}
