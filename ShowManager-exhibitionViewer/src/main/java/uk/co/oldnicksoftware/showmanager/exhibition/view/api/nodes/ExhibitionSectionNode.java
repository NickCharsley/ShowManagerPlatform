/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api.nodes;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.showmanager.api.capabilities.RemovableEntityCapability;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionSectionCollection;
import uk.co.oldnicksoftware.showmanager.domain.ExhibitionSection;
import uk.co.oldnicksoftware.showmanager.domain.Section;

/**
 *
 * @author nick
 */
public class ExhibitionSectionNode extends BeanNode {

    public ExhibitionSectionNode(ExhibitionSection exhibitionSection,ExhibitionSectionCollection exhibitionSectionCollection) throws IntrospectionException {
        this(exhibitionSection, exhibitionSectionCollection, new InstanceContent());
    }
    
    private ExhibitionSectionNode(ExhibitionSection exhibitionSection,ExhibitionSectionCollection exhibitionSectionCollection, InstanceContent instanceContent)  throws IntrospectionException {
        super(exhibitionSection, Children.LEAF, new AbstractLookup(instanceContent)); 
        instanceContent.add(exhibitionSection);
        instanceContent.add(exhibitionSectionCollection);
//        newExhibition=new NewExhibitionWizardAction();
//        instanceContent.add(newExhibition);
    }
    
    @Override
    public Action[] getActions(boolean context) {
        List exhibitionSectionActions = Utilities.actionsForPath("Actions/LeafExhibitionSection");
        return (Action[]) exhibitionSectionActions.toArray(new Action[exhibitionSectionActions.size()]);    
    }
    
    @Override
    public boolean canDestroy() {
        return true;
    }
    
    @Override
    public void destroy() throws IOException {
        try {
            ExhibitionSection exhibitionSection = getLookup().lookup(ExhibitionSection.class);
            ExhibitionSectionCollection collection = getLookup().lookup(ExhibitionSectionCollection.class);
            RemovableEntityCapability cec = collection.getLookup().lookup(RemovableEntityCapability.class);
            cec.remove(exhibitionSection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Notify the NodeListener in the RootNodeChildFactory,
        //where nodeDestroyed will call refresh on the ChildFactory:
        fireNodeDestroyed();
    }

    @Override
    public String getDisplayName(){
        ExhibitionSection exhibitionSection = getLookup().lookup(ExhibitionSection.class);
        Section section=exhibitionSection.getSectionID();
        
        if (exhibitionSection == null) return "Unknown Node";
        if (exhibitionSection == null) return "Unknown Name for Section ".concat(exhibitionSection.getSectionNumber());
        String name=exhibitionSection.getSectionNumber()
                .concat("] ")
                .concat(section.getName());
        if (!exhibitionSection.getDescription().equals("")){
            name=name
                    .concat(" (")
                    .concat(exhibitionSection.getDescription())
                    .concat(")");
        }
        return name;
    }
    
    @Override
    public String getHtmlDisplayName(){
        ExhibitionSection exhibitionSection = getLookup().lookup(ExhibitionSection.class);
        Section section=exhibitionSection.getSectionID();
        
        if (exhibitionSection == null) return "Unknown Node";
        if (exhibitionSection == null) return "Unknown Name for Section ".concat(exhibitionSection.getSectionNumber());
        String name="<b>"
                .concat(exhibitionSection.getSectionNumber())
                .concat("]</b> ")
                .concat(section.getName());
        if (!exhibitionSection.getDescription().equals("")){
            name=name
                    .concat(" <em>(")
                    .concat(exhibitionSection.getDescription())
                    .concat(")</em>");
        }
        return name;
    }
}
