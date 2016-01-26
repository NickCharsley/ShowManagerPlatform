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
@Table(name = "class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findById", query = "SELECT c FROM Class c WHERE c.id = :id"),
    @NamedQuery(name = "Class.findByName", query = "SELECT c FROM Class c WHERE c.name = :name"),
    @NamedQuery(name = "Class.findByDescription", query = "SELECT c FROM Class c WHERE c.description = :description")})
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classID")
    private Collection<ExhibitionClass> exhibitionClassCollection;

    private boolean updateCycle=true;
    
    public Class() {
        this(null,null);
    }

    public Class(Integer id) {
        this(id,null);
    }

    public Class(Integer id, String name) {
        this.id = id;
        this.name = name;
        setExhibitionClassCollection(new ArrayList());
    }
    
    public void unlink(){
        for (ExhibitionClass exhibitionClass:exhibitionClassCollection){
            unlink(exhibitionClass);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void link(ExhibitionClass exhibitionClass){
        if (updateCycle) return;
        if (exhibitionClass==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionClassCollection.contains(exhibitionClass)){
            exhibitionClassCollection.add(exhibitionClass);
            exhibitionClass.link(this);
        }
        updateCycle=false;
    }
        
    public void unlink(ExhibitionClass exhibitionClass){
        if (updateCycle) return;
        if (exhibitionClass==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionClassCollection.contains(exhibitionClass)){
            exhibitionClassCollection.remove(exhibitionClass);
            exhibitionClass.unlink(this);
        }
        updateCycle=false;        
    }

    @XmlTransient
    public Collection<ExhibitionClass> getExhibitionClassCollection() {
        return exhibitionClassCollection;
    }

    private void setExhibitionClassCollection(Collection<ExhibitionClass> exhibitionClassCollection) {
        this.exhibitionClassCollection = exhibitionClassCollection;
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
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Class[ id=" + id + " ]";
    }
    
}
