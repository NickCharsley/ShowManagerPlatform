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
@Table(name = "exhibition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibition.findAll", query = "SELECT e FROM Exhibition e"),
    @NamedQuery(name = "Exhibition.findById", query = "SELECT e FROM Exhibition e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibition.findByName", query = "SELECT e FROM Exhibition e WHERE e.name = :name")})
public class Exhibition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "showID")
    private Collection<Defaults> defaultsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<Exhibitionsection> exhibitionsectionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<Trophy> trophyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<Exhibitionexhibitor> exhibitionexhibitorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<Exhibitionclass> exhibitionclassCollection;

    public Exhibition() {
    }

    public Exhibition(Integer id) {
        this.id = id;
    }

    public Exhibition(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public Collection<Defaults> getDefaultsCollection() {
        return defaultsCollection;
    }

    public void setDefaultsCollection(Collection<Defaults> defaultsCollection) {
        this.defaultsCollection = defaultsCollection;
    }

    @XmlTransient
    public Collection<Exhibitionsection> getExhibitionsectionCollection() {
        return exhibitionsectionCollection;
    }

    public void setExhibitionsectionCollection(Collection<Exhibitionsection> exhibitionsectionCollection) {
        this.exhibitionsectionCollection = exhibitionsectionCollection;
    }

    @XmlTransient
    public Collection<Trophy> getTrophyCollection() {
        return trophyCollection;
    }

    public void setTrophyCollection(Collection<Trophy> trophyCollection) {
        this.trophyCollection = trophyCollection;
    }

    @XmlTransient
    public Collection<Exhibitionexhibitor> getExhibitionexhibitorCollection() {
        return exhibitionexhibitorCollection;
    }

    public void setExhibitionexhibitorCollection(Collection<Exhibitionexhibitor> exhibitionexhibitorCollection) {
        this.exhibitionexhibitorCollection = exhibitionexhibitorCollection;
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
        if (!(object instanceof Exhibition)) {
            return false;
        }
        Exhibition other = (Exhibition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibition[ id=" + id + " ]";
    }
    
}
