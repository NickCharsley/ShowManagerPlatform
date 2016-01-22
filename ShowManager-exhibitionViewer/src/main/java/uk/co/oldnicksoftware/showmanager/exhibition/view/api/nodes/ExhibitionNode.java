/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api.nodes;

import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import uk.co.oldnicksoftware.showmanager.exhibition.view.api.factories.ExhibitionNodeChildFactory;
import uk.co.oldnicksoftware.showmanager.exhibition.view.wizard.NewExhibitionWizardAction;

/**
 *
 * @author nick
 */
public class ExhibitionNode extends BeanNode {
    private NewExhibitionWizardAction newExhibition;
    
    public ExhibitionNode(Exhibition exhibition, ExhibitionCollection query)  throws IntrospectionException {
        this(exhibition, query, new InstanceContent());
    }
    
    private ExhibitionNode(final Exhibition exhibition, final ExhibitionCollection query, InstanceContent instanceContent)  throws IntrospectionException {
        super(exhibition, (exhibition.getExhibitionSectionCollection().isEmpty()?Children.LEAF:Children.create(new ExhibitionNodeChildFactory(exhibition), false)), new AbstractLookup(instanceContent)); 
        instanceContent.add(exhibition);
        instanceContent.add(query);
        instanceContent.add(new ReloadableViewCapability() {
            @Override
            public void reloadChildren() throws Exception {
                String newName = exhibition.getName();
                setDisplayName(newName);
            }
        });
        newExhibition=new NewExhibitionWizardAction();
        instanceContent.add(newExhibition);        
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{newExhibition};
    }
    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{(SystemAction.get(DeleteAction.class))};
    }
    @Override
    public boolean canDestroy() {
        return true;
    }
    @Override
    public void destroy() throws IOException {
        Exhibition exhibition = getLookup().lookup(Exhibition.class);
        ExhibitionCollection query = getLookup().lookup(ExhibitionCollection.class);
        RemovableEntityCapability cec = query.getLookup().lookup(RemovableEntityCapability.class);
        try {
            cec.remove(exhibition);
        } catch (Exception e) {
        }
        //Notify the NodeListener in the RootNodeChildFactory,
        //where nodeDestroyed will call refresh on the ChildFactory:
        fireNodeDestroyed();
    }
    
    @Override
    public String getDisplayName(){
        Exhibition exhibition = getLookup().lookup(Exhibition.class);
        if (exhibition == null) return "Unknown Node";
        String name=exhibition.getName();
        if (exhibition.isDefault()){
            name=name.concat(" (Default)");
        }
        return name;
    }
    
    @Override
    public String getHtmlDisplayName(){
        Exhibition exhibition = getLookup().lookup(Exhibition.class);
        if (exhibition == null) return "Unknown Node";
        String name=exhibition.getName();
        if (exhibition.isDefault()){
            name="<b>";
            name=name.concat(exhibition.getName().concat(" <em>(Default)</em></b>"));
        }
        return name;        
    }
}
