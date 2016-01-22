/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import cucumber.api.jelly.Helper;
import javax.swing.JTextField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author nick
 */
abstract class BaseWindowHelper<E> extends BaseHelper<E>{
    
    protected BaseWindowHelper(String targetName){
        super(targetName);
    }

    abstract public void display();
    
    protected String getTextField(String field){
        JLabelOperator labelOper = new JLabelOperator((ContainerOperator)operator(),field);
        assertThat("Found the field "+field,labelOper,is(notNullValue()));
        JTextFieldOperator txtOper = new JTextFieldOperator((JTextField)labelOper.getLabelFor());
        assertThat("Found the field "+field,txtOper,is(notNullValue()));
        return txtOper.getText();
    }
    
    /**
     * set the contents of the Text Field.
     * N.B. this totally replaces the the current text!
     * 
     * @param field The field to replace
     * @param value The value to replace it with
     */
    protected void setTextField(String field,String value){     
        JLabelOperator labelOper = new JLabelOperator((ContainerOperator)operator(),field);
        assertThat("Found the field "+field,labelOper,is(notNullValue()));
        JTextFieldOperator txtOper = new JTextFieldOperator((JTextField)labelOper.getLabelFor());
        assertThat("Found the field "+field,txtOper,is(notNullValue()));
        txtOper.clickMouse();
        txtOper.selectAll();
        for(char c: value.toCharArray()){
            txtOper.typeKey(c);
        }        
    }        
}
