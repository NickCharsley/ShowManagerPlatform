/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api;

import java.util.List;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

/**
 *
 * @author nick
 */
public interface ExhibitionCollection extends EntityCollection {
    public List getExhibitions();
    public Exhibition getExhibition(Exhibition search);    
}
