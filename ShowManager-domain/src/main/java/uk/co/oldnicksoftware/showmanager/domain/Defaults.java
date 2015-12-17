/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nick
 */
@Entity
@Table(name = "defaults")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Defaults.findAll", query = "SELECT d FROM Defaults d"),
    @NamedQuery(name = "Defaults.findById", query = "SELECT d FROM Defaults d WHERE d.id = :id"),
    @NamedQuery(name = "Defaults.findByShowName", query = "SELECT d FROM Defaults d WHERE d.showName = :showName")})
public class Defaults implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ShowName")
    private String showName;
    @JoinColumn(name = "ShowID", referencedColumnName = "ID")
    @ManyToOne
    private Exhibition showID;

    public Defaults() {
    }

    public Defaults(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Exhibition getShowID() {
        return showID;
    }

    public void setShowID(Exhibition showID) {
        this.showID = showID;
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
        if (!(object instanceof Defaults)) {
            return false;
        }
        Defaults other = (Defaults) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Defaults[ id=" + id + " ]";
    }
    
}