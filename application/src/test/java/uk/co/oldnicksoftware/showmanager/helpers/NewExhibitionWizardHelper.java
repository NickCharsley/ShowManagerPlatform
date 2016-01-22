/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import cucumber.api.jelly.Helper;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.WizardOperator;
import org.netbeans.jellytools.actions.ActionNoBlock;
import org.netbeans.jemmy.operators.*;

/**
 *
 * @author nick
 */
public class NewExhibitionWizardHelper extends BaseWindowHelper<WizardOperator>{
    
    public NewExhibitionWizardHelper(){
        super("New Exhibition");
    }

    @Override
    protected WizardOperator operator(){
        return new WizardOperator(targetName);
    }
    
    private JButtonOperator toolbarItem(String toolbar,String item){
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        ContainerOperator tbo=mwo.getToolbar(toolbar);        
        assertThat("Found Toolbar "+toolbar,tbo,is(notNullValue()));
        JButtonOperator ibo=mwo.getToolbarButton(tbo, item);
        assertThat("Found "+item+" on toolbar "+toolbar,ibo,is(notNullValue()));
        return ibo;
    }
    
    @Override
    public void display(){
        new ActionNoBlock("File|New Exhibition", null).performMenu();
    }
        
    public void setName(String name){
        setTextField("Name :",name);
    }
    
    public void setDefault(boolean status){
        String field="Default :";
        JLabelOperator labelOper = new JLabelOperator(operator(),field);

        assertThat("Found the field "+field,labelOper,is(notNullValue()));
        JCheckBoxOperator cbOper=new JCheckBoxOperator((JCheckBox)labelOper.getLabelFor());
        assertThat("Found the field "+field,cbOper,is(notNullValue()));
        cbOper.changeSelection(status);        
    }
    
    public void cancel(){
        operator().cancel();
    }
    
    public void finish(){
        operator().finish();
    }
    
    public void assertErrorContains(String error){     
//        assertThat(errors+" contains "+error,errors.contains(error),is(true));
    }
}
