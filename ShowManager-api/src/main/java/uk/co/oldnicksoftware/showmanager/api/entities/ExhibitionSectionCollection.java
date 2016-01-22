/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.api.entities;

import java.util.List;
import uk.co.oldnicksoftware.showmanager.domain.ExhibitionSection;

/**
 *
 * @author nick
 */
public interface ExhibitionSectionCollection extends EntityCollection<ExhibitionSection> {
    public List getExhibitionSections();
    public ExhibitionSection getExhibitionSection(ExhibitionSection search);    
}
