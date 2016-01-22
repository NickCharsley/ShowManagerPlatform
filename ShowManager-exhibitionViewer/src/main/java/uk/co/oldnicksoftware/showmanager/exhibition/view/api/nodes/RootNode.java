/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api.nodes;

import uk.co.oldnicksoftware.showmanager.exhibition.view.api.factories.RootNodeChildFactory;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.*;
import uk.co.oldnicksoftware.showmanager.exhibition.view.wizard.NewExhibitionWizardAction;

/**
 *
 * @author nick
 */
public class RootNode extends AbstractNode {
    private ExhibitionCollection query;
    private InstanceContent instanceContent;
    private NewExhibitionWizardAction newExhibition;
    
    public RootNode(ExhibitionCollection query) {
        this(query, new InstanceContent());
    }
    private RootNode(ExhibitionCollection query, InstanceContent ic) {
        super(Children.create(new RootNodeChildFactory(query), true), new AbstractLookup(ic));
        this.query = query;
        this.instanceContent = ic;
        // Add a new ability for this node to be reloaded
        this.instanceContent.add(new ReloadableViewCapability() {
            @Override
            public void reloadChildren() throws Exception {
                // To reload this node just set a new set of children
                // using a RootNodeChildFactory object, that retrieves
                // children asynchronously                
                setChildren(Children.create(new RootNodeChildFactory(RootNode.this.query), false));                         
            }
        });
        newExhibition=new NewExhibitionWizardAction();
        instanceContent.add(newExhibition);
    }
 
    @Override
    public String getDisplayName() {
        return "Exhibitions";
    }
    
    @Override
    public Action[] getActions(boolean context) {
        List exhibitionActions = Utilities.actionsForPath("Actions/Exhibition");
        return (Action[]) exhibitionActions.toArray(new Action[exhibitionActions.size()]);    
    }
    
    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{newExhibition};
    }
    
}
