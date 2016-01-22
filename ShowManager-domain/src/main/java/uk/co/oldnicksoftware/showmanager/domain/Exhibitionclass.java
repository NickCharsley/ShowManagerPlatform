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
@Table(name = "exhibitionclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitionclass.findAll", query = "SELECT e FROM Exhibitionclass e"),
    @NamedQuery(name = "Exhibitionclass.findById", query = "SELECT e FROM Exhibitionclass e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibitionclass.findByClassNumber", query = "SELECT e FROM Exhibitionclass e WHERE e.classNumber = :classNumber")})
public class Exhibitionclass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ClassNumber")
    private String classNumber;
    @OneToMany(mappedBy = "exhibitionClassID")
    private Collection<Exhibitiontrophyclass> exhibitiontrophyclassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionClassID")
    private Collection<Exhibitionclassprize> exhibitionclassprizeCollection;
    @JoinColumn(name = "ClassID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Class classID;
    @JoinColumn(name = "ExhibitionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibition exhibitionID;
    @JoinColumn(name = "ExhibitionSectionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ExhibitionSection exhibitionSectionID;

    public Exhibitionclass() {
    }

    public Exhibitionclass(Integer id) {
        this.id = id;
    }

    public Exhibitionclass(Integer id, String classNumber) {
        this.id = id;
        this.classNumber = classNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    @XmlTransient
    public Collection<Exhibitiontrophyclass> getExhibitiontrophyclassCollection() {
        return exhibitiontrophyclassCollection;
    }

    public void setExhibitiontrophyclassCollection(Collection<Exhibitiontrophyclass> exhibitiontrophyclassCollection) {
        this.exhibitiontrophyclassCollection = exhibitiontrophyclassCollection;
    }

    @XmlTransient
    public Collection<Exhibitionclassprize> getExhibitionclassprizeCollection() {
        return exhibitionclassprizeCollection;
    }

    public void setExhibitionclassprizeCollection(Collection<Exhibitionclassprize> exhibitionclassprizeCollection) {
        this.exhibitionclassprizeCollection = exhibitionclassprizeCollection;
    }

    public Class getClassID() {
        return classID;
    }

    public void setClassID(Class classID) {
        this.classID = classID;
    }

    public Exhibition getExhibitionID() {
        return exhibitionID;
    }

    public void setExhibitionID(Exhibition exhibitionID) {
        this.exhibitionID = exhibitionID;
    }

    public ExhibitionSection getExhibitionSectionID() {
        return exhibitionSectionID;
    }

    public void setExhibitionSectionID(ExhibitionSection exhibitionSectionID) {
        this.exhibitionSectionID = exhibitionSectionID;
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
        if (!(object instanceof Exhibitionclass)) {
            return false;
        }
        Exhibitionclass other = (Exhibitionclass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitionclass[ id=" + id + " ]";
    }
    
}
