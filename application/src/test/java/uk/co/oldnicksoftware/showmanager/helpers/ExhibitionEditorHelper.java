/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JCheckBoxOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author nick
 */
public class ExhibitionEditorHelper extends BaseWindowHelper<TopComponentOperator>{
    
    public ExhibitionEditorHelper(){
        super("ExhibitionEditor Window");
    }
    
    @Override
    protected TopComponentOperator operator(){
        return  new TopComponentOperator(targetName);
    }

    @Override
    public void display() {
        //NOP should always be displayed...
    }

    public void assertNameIs(String name){
        assertThat("Name is "+name,getName(),is(name));
    }
    
    public String getName(){
        return getTextField("Name:");
    }
    
    public void setName(String name){
        setTextField("Name:",name);
    }
    
    public void assertDefaultIs(boolean value){
        assertThat("Default is "+(value?"checked":"unchecked"),getDefault(),is(value));
    }
    
    public boolean getDefault(){
        String field="Default:";
        JLabelOperator labelOper = new JLabelOperator(operator(),field);

        assertThat("Found the field "+field,labelOper,is(notNullValue()));
        JCheckBoxOperator cbOper=new JCheckBoxOperator((JCheckBox)labelOper.getLabelFor());
        assertThat("Found the field "+field,cbOper,is(notNullValue()));
        return cbOper.isSelected();                
    }
    
    public void setDefault(boolean status){
        String field="Default:";
        JLabelOperator labelOper = new JLabelOperator(operator(),field);

        assertThat("Found the field "+field,labelOper,is(notNullValue()));
        JCheckBoxOperator cbOper=new JCheckBoxOperator((JCheckBox)labelOper.getLabelFor());
        assertThat("Found the field "+field,cbOper,is(notNullValue()));
        cbOper.changeSelection(status);        
    }
    
}
