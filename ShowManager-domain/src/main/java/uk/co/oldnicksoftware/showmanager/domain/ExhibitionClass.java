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
public class ExhibitionClass implements Serializable {
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
    private Collection<ExhibitionTrophyClass> exhibitionTrophyClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionClassID")
    private Collection<ExhibitionClassPrize> exhibitionClassPrizeCollection;
    @JoinColumn(name = "ClassID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Class classID;
    @JoinColumn(name = "ExhibitionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Exhibition exhibitionID;
    @JoinColumn(name = "ExhibitionSectionID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ExhibitionSection exhibitionSectionID;

    private boolean updateCycle = false;
    
    public ExhibitionClass() {
        this(null,null);
    }

    public ExhibitionClass(Integer id) {
        this(id,null);
    }

    public ExhibitionClass(Integer id, String classNumber) {
        this.id = id;
        this.classNumber = classNumber;
        setExhibitionClassPrizeCollection(new ArrayList());
        setExhibitionTrophyClassCollection(new ArrayList());
    }

    public void unlink(){
        /*Unlink All*/
        unlink(classID);
        unlink(exhibitionID);
        unlink(exhibitionSectionID);
        for (ExhibitionClassPrize exhibitionClassPrize:exhibitionClassPrizeCollection){
            unlink(exhibitionClassPrize);
        }
        for (ExhibitionTrophyClass exhibitionTrophyClass:exhibitionTrophyClassCollection){
            unlink(exhibitionTrophyClass);
        }
        
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
    public Collection<ExhibitionTrophyClass> getExhibitiontrophyclassCollection() {
        return exhibitionTrophyClassCollection;
    }

    private void setExhibitionTrophyClassCollection(Collection<ExhibitionTrophyClass> exhibitiontrophyclassCollection) {
        this.exhibitionTrophyClassCollection = exhibitiontrophyclassCollection;
    }

    public void link(ExhibitionClassPrize exhibitionClassPrize){
        if (updateCycle) return;
        if (exhibitionClassPrize==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionClassPrizeCollection.contains(exhibitionClassPrize)){
            exhibitionClassPrizeCollection.add(exhibitionClassPrize);
            exhibitionClassPrize.link(this);
        }
        updateCycle=false;
    }
        
    public void unlink(ExhibitionClassPrize exhibitionClassPrize){
        if (updateCycle) return;
        if (exhibitionClassPrize==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionClassPrizeCollection.contains(exhibitionClassPrize)){
            exhibitionClassPrizeCollection.remove(exhibitionClassPrize);
            exhibitionClassPrize.unlink(this);
        }
        updateCycle=false;        
    }

    @XmlTransient
    public Collection<ExhibitionClassPrize> getExhibitionclassprizeCollection() {
        return exhibitionClassPrizeCollection;
    }

    private void setExhibitionClassPrizeCollection(Collection<ExhibitionClassPrize> exhibitionclassprizeCollection) {
        this.exhibitionClassPrizeCollection = exhibitionclassprizeCollection;
    }

    public Class getClassID() {
        return classID;
    }
    
    public final void unlink(Class classID) {
        link((Class) null);
    }
    
    public final void link(Class classID) {
        if (updateCycle) return;
        updateCycle=true;
        if (this.classID != classID){
            if (this.classID != null){
                this.classID.unlink(this);
            }
        }
        if (classID!=null){
            classID.link(this);
        }       
        this.classID=classID;
        updateCycle=false;
    }
    
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

    public Exhibition getExhibitionID() {
        return exhibitionID;
    }

    public final void unlink(ExhibitionSection exhibitionSectionID) {
        link((ExhibitionSection) null);
    }
    
    public final void link(ExhibitionSection exhibitionSectionID) {
        if (updateCycle) return;
        updateCycle=true;
        if (this.exhibitionSectionID != exhibitionSectionID){
            if (this.exhibitionSectionID != null){
                this.exhibitionSectionID.unlink(this);
            }
        }
        if (exhibitionSectionID!=null){
            exhibitionSectionID.link(this);
        }       
        this.exhibitionSectionID=exhibitionSectionID;
        updateCycle=false;
    }

    public ExhibitionSection getExhibitionSectionID() {
        return exhibitionSectionID;
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
        if (!(object instanceof ExhibitionClass)) {
            return false;
        }
        ExhibitionClass other = (ExhibitionClass) object;
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
