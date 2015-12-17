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
@Table(name = "sponsorship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sponsorship.findAll", query = "SELECT s FROM Sponsorship s"),
    @NamedQuery(name = "Sponsorship.findById", query = "SELECT s FROM Sponsorship s WHERE s.id = :id"),
    @NamedQuery(name = "Sponsorship.findByName", query = "SELECT s FROM Sponsorship s WHERE s.name = :name"),
    @NamedQuery(name = "Sponsorship.findByPrize", query = "SELECT s FROM Sponsorship s WHERE s.prize = :prize")})
public class Sponsorship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Prize")
    private String prize;
    @JoinColumn(name = "ExhibitionClassPrizeID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibitionclassprize exhibitionClassPrizeID;

    public Sponsorship() {
    }

    public Sponsorship(Integer id) {
        this.id = id;
    }

    public Sponsorship(Integer id, String name, String prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
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

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Exhibitionclassprize getExhibitionClassPrizeID() {
        return exhibitionClassPrizeID;
    }

    public void setExhibitionClassPrizeID(Exhibitionclassprize exhibitionClassPrizeID) {
        this.exhibitionClassPrizeID = exhibitionClassPrizeID;
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
        if (!(object instanceof Sponsorship)) {
            return false;
        }
        Sponsorship other = (Sponsorship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Sponsorship[ id=" + id + " ]";
    }
    
}
