/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "exhibitionsection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitionsection.findAll", query = "SELECT e FROM Exhibitionsection e"),
    @NamedQuery(name = "Exhibitionsection.findById", query = "SELECT e FROM Exhibitionsection e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibitionsection.findBySectionNumber", query = "SELECT e FROM Exhibitionsection e WHERE e.sectionNumber = :sectionNumber"),
    @NamedQuery(name = "Exhibitionsection.findByDescription", query = "SELECT e FROM Exhibitionsection e WHERE e.description = :description")})
public class Exhibitionsection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "SectionNumber")
    private String sectionNumber;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "ExhibitionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibition exhibitionID;
    @JoinColumn(name = "SectionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Section sectionID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionSectionID")
    private Collection<Exhibitionclass> exhibitionclassCollection;

    public Exhibitionsection() {
    }

    public Exhibitionsection(Integer id) {
        this.id = id;
    }

    public Exhibitionsection(Integer id, String sectionNumber) {
        this.id = id;
        this.sectionNumber = sectionNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exhibition getExhibitionID() {
        return exhibitionID;
    }

    public void setExhibitionID(Exhibition exhibitionID) {
        this.exhibitionID = exhibitionID;
    }

    public Section getSectionID() {
        return sectionID;
    }

    public void setSectionID(Section sectionID) {
        this.sectionID = sectionID;
    }

    @XmlTransient
    public Collection<Exhibitionclass> getExhibitionclassCollection() {
        return exhibitionclassCollection;
    }

    public void setExhibitionclassCollection(Collection<Exhibitionclass> exhibitionclassCollection) {
        this.exhibitionclassCollection = exhibitionclassCollection;
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
        if (!(object instanceof Exhibitionsection)) {
            return false;
        }
        Exhibitionsection other = (Exhibitionsection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitionsection[ id=" + id + " ]";
    }
    
}