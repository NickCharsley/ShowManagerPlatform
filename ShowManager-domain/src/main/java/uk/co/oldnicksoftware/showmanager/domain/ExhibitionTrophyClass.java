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
public class ExhibitionTrophyClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ExhibitionClassID", referencedColumnName = "ID")
    @ManyToOne
    private ExhibitionClass exhibitionClassID;
    @JoinColumn(name = "TrophyID", referencedColumnName = "ID")
    @ManyToOne
    private Trophy trophyID;

    private boolean updateCycle = false;

    public ExhibitionTrophyClass() {
    }

    public ExhibitionTrophyClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExhibitionClass getExhibitionClassID() {
        return exhibitionClassID;
    }

    public final void unlink(ExhibitionClass exhibitionClassID) {
        link((ExhibitionClass) null);
    }
    
    public final void link(ExhibitionClass exhibitionClassID) {
        if (updateCycle) return;
        updateCycle=true;
        if (this.exhibitionClassID != exhibitionClassID){
            if (this.exhibitionClassID != null){
                this.exhibitionClassID.unlink(this);
            }
        }
        if (exhibitionClassID!=null){
            exhibitionClassID.link(this);
        }       
        this.exhibitionClassID=exhibitionClassID;
        updateCycle=false;
    }

    public Trophy getTrophyID() {
        return trophyID;
    }

    public final void unlink(Trophy trophyID) {
        link((Trophy) null);
    }
    
    public final void link(Trophy trophyID) {
        if (updateCycle) return;
        updateCycle=true;
        if (this.trophyID != trophyID){
            if (this.trophyID != null){
                this.trophyID.unlink(this);
            }
        }
        if (trophyID!=null){
            trophyID.link(this);
        }       
        this.trophyID=trophyID;
        updateCycle=false;
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
        if (!(object instanceof ExhibitionTrophyClass)) {
            return false;
        }
        ExhibitionTrophyClass other = (ExhibitionTrophyClass) object;
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
