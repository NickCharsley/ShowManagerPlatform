/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api.factories;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.nodes.NodeEvent;
import org.openide.nodes.NodeListener;
import org.openide.nodes.NodeMemberEvent;
import org.openide.nodes.NodeReorderEvent;
import org.openide.util.Exceptions;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import uk.co.oldnicksoftware.showmanager.exhibition.view.api.nodes.ExhibitionNode;

/**
 *
 * @author nick
 */
public class RootNodeChildFactory extends ChildFactory<Exhibition> implements NodeListener{
    private final ExhibitionCollection query;
    public RootNodeChildFactory(ExhibitionCollection query) {
        this.query = query;
    }
    @Override
    protected boolean createKeys(List<Exhibition> list) {
        // get this ability from the lookup ...
        ReloadableEntityCapability r = query.getLookup().lookup(ReloadableEntityCapability.class);
        // ... and  use the ability
        if (r != null) {
            try {
                r.reload();
            } catch (Exception e) {
                // Empty
            }
        }
        // Now populate the list of child entities...
        list.addAll(query.getExhibitions());
        // And return true since we're all set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Exhibition key) {
        try {
            ExhibitionNode cn= new ExhibitionNode(key,query);
            cn.addNodeListener(this);
            return cn;
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null; 
    }

    @Override
    public void childrenAdded(NodeMemberEvent ev) {
    }

    @Override
    public void childrenRemoved(NodeMemberEvent ev) {
    }

    @Override
    public void childrenReordered(NodeReorderEvent ev) {
    }

    @Override
    public void nodeDestroyed(NodeEvent ev) {
        refresh(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
