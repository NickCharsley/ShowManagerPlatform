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
@Table(name = "exhibitiontrophyclass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitiontrophyclass.findAll", query = "SELECT e FROM Exhibitiontrophyclass e"),
    @NamedQuery(name = "Exhibitiontrophyclass.findById", query = "SELECT e FROM Exhibitiontrophyclass e WHERE e.id = :id")})
public class Exhibitiontrophyclass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ExhibitionClassID", referencedColumnName = "ID")
    @ManyToOne
    private Exhibitionclass exhibitionClassID;
    @JoinColumn(name = "TrophyID", referencedColumnName = "ID")
    @ManyToOne
    private Trophy trophyID;

    public Exhibitiontrophyclass() {
    }

    public Exhibitiontrophyclass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Exhibitionclass getExhibitionClassID() {
        return exhibitionClassID;
    }

    public void setExhibitionClassID(Exhibitionclass exhibitionClassID) {
        this.exhibitionClassID = exhibitionClassID;
    }

    public Trophy getTrophyID() {
        return trophyID;
    }

    public void setTrophyID(Trophy trophyID) {
        this.trophyID = trophyID;
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
        if (!(object instanceof Exhibitiontrophyclass)) {
            return false;
        }
        Exhibitiontrophyclass other = (Exhibitiontrophyclass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitiontrophyclass[ id=" + id + " ]";
    }
    
}
