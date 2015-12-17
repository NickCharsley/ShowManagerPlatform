/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "exhibitionexhibitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitionexhibitor.findAll", query = "SELECT e FROM Exhibitionexhibitor e"),
    @NamedQuery(name = "Exhibitionexhibitor.findById", query = "SELECT e FROM Exhibitionexhibitor e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibitionexhibitor.findByExhibitorNumber", query = "SELECT e FROM Exhibitionexhibitor e WHERE e.exhibitorNumber = :exhibitorNumber")})
public class Exhibitionexhibitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ExhibitorNumber")
    private int exhibitorNumber;
    @OneToMany(mappedBy = "exhibitionExhibitorID")
    private Collection<Exhibitionclassprize> exhibitionclassprizeCollection;
    @JoinColumn(name = "ExhibitionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibition exhibitionID;
    @JoinColumn(name = "ExhibitorID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibitor exhibitorID;

    public Exhibitionexhibitor() {
    }

    public Exhibitionexhibitor(Integer id) {
        this.id = id;
    }

    public Exhibitionexhibitor(Integer id, int exhibitorNumber) {
        this.id = id;
        this.exhibitorNumber = exhibitorNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getExhibitorNumber() {
        return exhibitorNumber;
    }

    public void setExhibitorNumber(int exhibitorNumber) {
        this.exhibitorNumber = exhibitorNumber;
    }

    @XmlTransient
    public Collection<Exhibitionclassprize> getExhibitionclassprizeCollection() {
        return exhibitionclassprizeCollection;
    }

    public void setExhibitionclassprizeCollection(Collection<Exhibitionclassprize> exhibitionclassprizeCollection) {
        this.exhibitionclassprizeCollection = exhibitionclassprizeCollection;
    }

    public Exhibition getExhibitionID() {
        return exhibitionID;
    }

    public void setExhibitionID(Exhibition exhibitionID) {
        this.exhibitionID = exhibitionID;
    }

    public Exhibitor getExhibitorID() {
        return exhibitorID;
    }

    public void setExhibitorID(Exhibitor exhibitorID) {
        this.exhibitorID = exhibitorID;
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
        if (!(object instanceof Exhibitionexhibitor)) {
            return false;
        }
        Exhibitionexhibitor other = (Exhibitionexhibitor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitionexhibitor[ id=" + id + " ]";
    }
    
}
