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
@Table(name = "trophy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trophy.findAll", query = "SELECT t FROM Trophy t"),
    @NamedQuery(name = "Trophy.findById", query = "SELECT t FROM Trophy t WHERE t.id = :id"),
    @NamedQuery(name = "Trophy.findByName", query = "SELECT t FROM Trophy t WHERE t.name = :name"),
    @NamedQuery(name = "Trophy.findByMember", query = "SELECT t FROM Trophy t WHERE t.member = :member")})
public class Trophy implements Serializable {
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
    @Column(name = "Member")
    private boolean member;
    @OneToMany(mappedBy = "trophyID")
    private Collection<ExhibitionTrophyClass> exhibitionTrophyClassCollection;
    @JoinColumn(name = "ExhibitionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibition exhibitionID;

    public Trophy() {
        this(null,null,false);
    }

    public Trophy(Integer id) {
        this(id,null,false);
    }

    public Trophy(Integer id, String name, boolean member) {
        this.id = id;
        this.name = name;
        this.member = member;
        this.setExhibitionTrophyClassCollection(new ArrayList());
    }
    
    public void unlink(){
        unlink(exhibitionID);
        for(ExhibitionTrophyClass exhibitionTrophyClass:exhibitionTrophyClassCollection){
            unlink(exhibitionTrophyClass);
        }
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

    public boolean getMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public void link(ExhibitionTrophyClass exhibitionTrophyClass){
        if (updateCycle) return;
        if (exhibitionTrophyClass==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionTrophyClassCollection.contains(exhibitionTrophyClass)){
            exhibitionTrophyClassCollection.add(exhibitionTrophyClass);
            exhibitionTrophyClass.link(this);
        }
        updateCycle=false;
    }
        
    public void unlink(ExhibitionTrophyClass exhibitionTrophyClass){
        if (updateCycle) return;
        if (exhibitionTrophyClass==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionTrophyClassCollection.contains(exhibitionTrophyClass)){
            exhibitionTrophyClassCollection.remove(exhibitionTrophyClass);
            exhibitionTrophyClass.unlink(this);
        }
        updateCycle=false;        
    }

    @XmlTransient
    public Collection<ExhibitionTrophyClass> getExhibitionTrophyClassCollection() {
        return exhibitionTrophyClassCollection;
    }

    private void setExhibitionTrophyClassCollection(Collection<ExhibitionTrophyClass> exhibitionTrophyClassCollection) {
        this.exhibitionTrophyClassCollection = exhibitionTrophyClassCollection;
    }

    public Exhibition getExhibitionID() {
        return exhibitionID;
    }

    private boolean updateCycle = false;
    
    public final void unlink(Exhibition exhibitionID) {
        link((Exhibition) null);
    }
    
    public final void link(Exhibition exhibitionID) {
        if (updateCycle) return;
        updateCycle=true;
        if (this.exhibitionID != exhibitionID){
            if (this.exhibitionID != null){
                this.exhibitionID.unlink(this);
            }
        }
        if (exhibitionID!=null){
            exhibitionID.link(this);
        }       
        this.exhibitionID=exhibitionID;
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
        if (!(object instanceof Trophy)) {
            return false;
        }
        Trophy other = (Trophy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Trophy[ id=" + id + " ]";
    }
    
}
