/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.api;

import java.io.IOException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.NewType;
import uk.co.oldnicksoftware.showmanager.api.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;
import static uk.co.oldnicksoftware.showmanager.exhibition.view.api.Bundle.*;

/**
 *
 * @author nick
 */
@NbBundle.Messages({
    "LBL_NewName_dialog=Exhibition Name:",
    "TITLE_NewExhibition_dialog=New Exhibition"})
public class NewExhibitionType  extends NewType {
    private final ExhibitionCollection query;
    private final Node node;
    private final boolean root;
    
    public NewExhibitionType(ExhibitionCollection query, Node node, boolean root) {
        this.query = query;
        this.node = node;
        this.root = root;
    }
    @Override
    public String getName() {
        return TITLE_NewExhibition_dialog();
    }
    @Override
    public void create() throws IOException {
/**/        
        NotifyDescriptor.InputLine msg = new NotifyDescriptor.InputLine(LBL_NewName_dialog(), TITLE_NewExhibition_dialog());
        Object result = DialogDisplayer.getDefault().notify(msg);
        if (!NotifyDescriptor.YES_OPTION.equals(result)) {
            return;
        }
        String fieldName = msg.getInputText();
                
        if (NotifyDescriptor.YES_OPTION.equals(result)) {
            try {
                //Create a new Exhibition object:
                Exhibition exhibition = new Exhibition();
                //This is Fake so we just generate a good random number..
                exhibition.setId(fieldName.length());
                exhibition.setName(fieldName);
                //Pass the exhibition to the query's implementation of the create capability: 
                CreatableEntityCapability cec = query.getLookup().lookup(CreatableEntityCapability.class);
                cec.create(exhibition);
                               
                //If the Node passed in is the root node, refresh the root node,
                //else refresh the child node only:
                if (!root) {
                    query.reload(node.getParentNode());
                } else {
                    query.reload(node);
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
/**/
    }
    
}