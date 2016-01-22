/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api.entities;

import java.util.List;
import uk.co.oldnicksoftware.showmanager.domain.Exhibition;

/**
 *
 * @author nick
 */
public interface ExhibitionCollection<E> extends EntityCollection<E> {
    public List getExhibitions();
    public Exhibition getExhibition(Exhibition search);    
}
