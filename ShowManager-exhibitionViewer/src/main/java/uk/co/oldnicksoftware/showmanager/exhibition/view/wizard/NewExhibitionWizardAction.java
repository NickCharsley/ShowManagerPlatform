/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.exhibition.view.wizard;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.*;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.NewType;
import uk.co.oldnicksoftware.showmanager.api.entities.DefaultsSingleton;
import uk.co.oldnicksoftware.showmanager.api.entities.ExhibitionCollection;
import uk.co.oldnicksoftware.showmanager.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

@ActionID(category="NewExhibitionWizard", id="uk.co.oldnicksoftware.showmanager.exhibition.view.wizard.NewExhibitionWizardAction")
@ActionRegistration(displayName="New Exhibition Wizard")

@ActionReference(path="Menu/File", position=10)

public final class NewExhibitionWizardAction extends NewType implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            create();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void create() throws IOException {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<>();
        panels.add(new ExhibitionWizardPanel1());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, false);                
                //jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                //jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()                
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle("New Exhibition");
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            ExhibitionCollection query = Lookup.getDefault().lookup(ExhibitionCollection.class);
            try {
                Exhibition exhibition = new Exhibition((String)wiz.getProperty("Exhibition Name"));
                CreatableEntityCapability cec = query.getLookup().lookup(CreatableEntityCapability.class);
                cec.create(exhibition);
                //if we get hear it's now safe to make it default as it really exists....
                if ((boolean)wiz.getProperty("Default")){
                    Lookup defaultLookup = Lookup.getDefault();
                    DefaultsSingleton defaultsSingleton = defaultLookup.lookup(DefaultsSingleton.class);
                    defaultsSingleton.getDefaults().link(exhibition);
                }
                query.reload();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
