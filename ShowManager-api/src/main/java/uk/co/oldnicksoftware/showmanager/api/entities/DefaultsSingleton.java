/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api.entities;

import uk.co.oldnicksoftware.showmanager.domain.Defaults;
/**
 *
 * @author nick
 */
public interface DefaultsSingleton extends EntityCollection {
    public Defaults getDefaults();    
}
