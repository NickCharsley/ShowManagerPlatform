/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.helpers;

/**
 *
 * @author nick
 */
public class FileToolBarHelper extends BaseToolbarHelper {
    public FileToolBarHelper(){
        super("&File");
    }
    public void clickSave(){
        this.clickToolbarButton("Save");
    }
}
