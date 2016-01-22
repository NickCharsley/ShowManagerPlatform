/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.wizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

public class ExhibitionWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private ExhibitionVisualPanel1 component;

    /**
     * Flag to indicate if the data in the panel is valid.
     */
    private boolean isValid=true;
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ExhibitionVisualPanel1 getComponent() {
        if (component == null) {
            component = new ExhibitionVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        // use wiz.putProperty to remember current panel state
        wiz.putProperty("Exhibition Name", getComponent().getExhibitionName());
        wiz.putProperty("Default", getComponent().getDefault());
    }

    @Override
    public void validate() throws WizardValidationException {
        
        String name = getComponent().getExhibitionName();
        if (name.equals("")){ 
            isValid=false;
            throw new WizardValidationException(null, "Name is compulsory.", null);       
        } 
        Lookup defaultLookup = Lookup.getDefault();
        ExhibitionCollection exhibitionCollection = defaultLookup.lookup(ExhibitionCollection.class);
        if (!exhibitionCollection.isAddable(new Exhibition(name))){
            isValid=false;
            throw new WizardValidationException(null, "This Exhibition already exists.", null);                   
        }       
    }

    
}
