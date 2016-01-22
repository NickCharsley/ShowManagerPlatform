/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api.entities;

import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author nick
 */
public interface EntityCollection<E> {
    public Lookup getLookup();
    public void reload();        
    public void addRootNode(Node rootNode);
    public void removeRootNode(Node rootNode);    
    public boolean isAddable(E Entity);
}
