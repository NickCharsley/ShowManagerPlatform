/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

import org.netbeans.jellytools.NbDialogOperator;

/**
 *
 * @author nick
 */
abstract class BaseOkCancelDialogHelper extends BaseHelper<NbDialogOperator>{    
    protected BaseOkCancelDialogHelper(String targetName){
        super(targetName);
    }
    
    @Override
    protected NbDialogOperator operator(){
        return new NbDialogOperator(targetName);
    }
        
    public void pressOkButton(){
        operator().ok();
    }

    public void pressCancelButton(){
        operator().cancel();
    }        
}
