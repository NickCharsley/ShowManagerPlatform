/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nick
 */
@Entity
@Table(name = "section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findById", query = "SELECT s FROM Section s WHERE s.id = :id"),
    @NamedQuery(name = "Section.findByName", query = "SELECT s FROM Section s WHERE s.name = :name")})
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionID")
    private Collection<ExhibitionSection> exhibitionSectionCollection;

    public Section() {
        this(null,null);
    }

    public Section(Integer id) {
        this(id,null);
    }

    public Section(Integer id, String name) {
        this.setExhibitionSectionCollection(new ArrayList());
        this.id = id;
        this.name = name;
    }

    public Section(String name) {
        this(null,name);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean updateCycle;

    public void link(ExhibitionSection exhibitionSection){
        if (updateCycle) return;
        if (exhibitionSection==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionSectionCollection.contains(exhibitionSection)){
            exhibitionSectionCollection.add(exhibitionSection);
            exhibitionSection.link(this);
        }
        updateCycle=false;
    }

    public void unlink(ExhibitionSection exhibitionSection){
        if (updateCycle) return;
        if (exhibitionSection==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionSectionCollection.contains(exhibitionSection)){
            exhibitionSectionCollection.remove(exhibitionSection);
            exhibitionSection.unlink(this);
        }
        updateCycle=false;        
    }
    
    @XmlTransient
    public Collection<ExhibitionSection> getExhibitionSectionCollection() {
        return exhibitionSectionCollection;
    }

    private void setExhibitionSectionCollection(Collection<ExhibitionSection> exhibitionsectionCollection) {
        this.exhibitionSectionCollection = exhibitionsectionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Section[ id=" + id + " ]";
    }
    
}
