/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api.factories;

import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.util.List;
import org.openide.nodes.*;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionSectionCollection;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import uk.co.oldnicksoftware.showmanager.domain.ExhibitionSection;
import uk.co.oldnicksoftware.showmanager.exhibition.view.api.nodes.ExhibitionSectionNode;

/**
 *
 * @author nick
 */
public class ExhibitionNodeChildFactory extends ChildFactory<ExhibitionSection> implements NodeListener {
    private final Exhibition exhibition;
    private final ExhibitionSectionCollection exhibitionSectionCollection;
    
    public ExhibitionNodeChildFactory(Exhibition exhibition){
        this.exhibition=exhibition;
        exhibitionSectionCollection = Lookup.getDefault().lookup(ExhibitionSectionCollection.class);            
    }
    
    @Override
    protected boolean createKeys(List<ExhibitionSection> toPopulate) {
        toPopulate.addAll(exhibition.getExhibitionSectionCollection());
        return true;
    }

    @Override
    protected Node createNodeForKey(ExhibitionSection key) {
        try {
            ExhibitionSectionNode cn= new ExhibitionSectionNode(key,exhibitionSectionCollection);
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
