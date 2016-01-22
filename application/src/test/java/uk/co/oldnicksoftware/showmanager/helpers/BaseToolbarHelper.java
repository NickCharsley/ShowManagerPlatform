/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JButtonOperator;

/**
 *
 * @author nick
 */
abstract class BaseToolbarHelper extends BaseHelper<ContainerOperator>{    
    protected BaseToolbarHelper(String targetName){
        super(targetName);
    }
    
    @Override
    protected ContainerOperator operator() {
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        return mwo.getToolbar(targetName);      
    }
    
    private JButtonOperator toolbarButton(String item){
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        return mwo.getToolbarButton(operator(), item);
    }
    
    public void clickToolbarButton(String item){
        JButtonOperator ibo=toolbarButton(item);
        assertThat("Found "+item+" on toolbar "+targetName,ibo,is(notNullValue()));
        ibo.push();
    }
}
