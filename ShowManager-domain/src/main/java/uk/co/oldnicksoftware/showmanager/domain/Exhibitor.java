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
@Table(name = "exhibitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitor.findAll", query = "SELECT e FROM Exhibitor e"),
    @NamedQuery(name = "Exhibitor.findById", query = "SELECT e FROM Exhibitor e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibitor.findBySurname", query = "SELECT e FROM Exhibitor e WHERE e.surname = :surname"),
    @NamedQuery(name = "Exhibitor.findByMember", query = "SELECT e FROM Exhibitor e WHERE e.member = :member"),
    @NamedQuery(name = "Exhibitor.findByTitle", query = "SELECT e FROM Exhibitor e WHERE e.title = :title"),
    @NamedQuery(name = "Exhibitor.findByInitials", query = "SELECT e FROM Exhibitor e WHERE e.initials = :initials")})
public class Exhibitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "Member")
    private boolean member;
    @Column(name = "Title")
    private String title;
    @Column(name = "Initials")
    private String initials;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitorID")
    private Collection<Exhibitionexhibitor> exhibitionexhibitorCollection;

    public Exhibitor() {
    }

    public Exhibitor(Integer id) {
        this.id = id;
    }

    public Exhibitor(Integer id, String surname, boolean member) {
        this.id = id;
        this.surname = surname;
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @XmlTransient
    public Collection<Exhibitionexhibitor> getExhibitionexhibitorCollection() {
        return exhibitionexhibitorCollection;
    }

    public void setExhibitionexhibitorCollection(Collection<Exhibitionexhibitor> exhibitionexhibitorCollection) {
        this.exhibitionexhibitorCollection = exhibitionexhibitorCollection;
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
        if (!(object instanceof Exhibitor)) {
            return false;
        }
        Exhibitor other = (Exhibitor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitor[ id=" + id + " ]";
    }
    
}
