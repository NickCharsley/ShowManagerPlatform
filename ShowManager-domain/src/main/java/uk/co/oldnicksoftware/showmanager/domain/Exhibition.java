/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.*;
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
    
    @OneToOne(mappedBy = "defaults")
    private Defaults defaults;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<ExhibitionSection> exhibitionSectionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<Trophy> trophyCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<ExhibitionExhibitor> exhibitionExhibitorCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionID")
    private Collection<ExhibitionClass> exhibitionClassCollection;

    public Exhibition() {
        this(null,null);
    }

    public Exhibition(Integer id) {
        this(id,null);
    }

    public Exhibition(Integer id, String name) {
        setExhibitionClassCollection(new CopyOnWriteArrayList());
        setExhibitionExhibitorCollection(new CopyOnWriteArrayList());
        setTrophyCollection(new CopyOnWriteArrayList());
        setExhibitionSectionCollection(new CopyOnWriteArrayList());

        this.id = id;
        this.name = name;
    }

    public Exhibition(String name) {
        this(null,name);
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
    
    private boolean updateCycle = false;

    public void unlink(){
        //Unlink Everything !!!        
        //N.B. we use the toArray() trick to prevent concurent updates
        unlink(defaults);
        for (Object exhibitionSection:exhibitionSectionCollection.toArray()) {
            unlink((ExhibitionSection)exhibitionSection);
        }
        for (Object exhibitionClass:exhibitionClassCollection.toArray()) {
            unlink((ExhibitionClass)exhibitionClass);
        }
        for (Object exhibitionExhibitor:exhibitionExhibitorCollection.toArray()){
            unlink((ExhibitionExhibitor)exhibitionExhibitor);
        }
        for (Object trophy:trophyCollection.toArray()){
            unlink((Trophy)trophy);
        }
    }
    
    public void unlink(Defaults defaults){
        link((Defaults)null);
    }
    
    public void link(Defaults defaults){
        if (updateCycle) return;
        updateCycle=true;
        if (this.defaults != defaults){
            if (this.defaults != null){
                this.defaults.unlink(this.defaults.getShowID());
            }
        }
        if (defaults!=null){
            defaults.link(this);
        }       
        this.defaults=defaults;
        updateCycle=false;
    }
    
    public Defaults getDefaults(){
        return defaults;
    }

    public boolean isDefault(){
        return defaults!=null;
    }

    public void link(ExhibitionSection exhibitionSection){
        if (updateCycle) return;
        if (exhibitionSection==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionSectionCollection.contains(exhibitionSection)){
            exhibitionSectionCollection.add(exhibitionSection);
            exhibitionSection.link(this);
        }
        updateCycle=false;
    }

    public void unlink(ExhibitionSection exhibitionSection){
        if (updateCycle) return;
        if (exhibitionSection==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionSectionCollection.contains(exhibitionSection)){
            exhibitionSectionCollection.remove(exhibitionSection);
            exhibitionSection.unlink(this);
        }
        updateCycle=false;        
    }    
    
    @XmlTransient
    public Collection<ExhibitionSection> getExhibitionSectionCollection() {
        return exhibitionSectionCollection;
    }

    private void setExhibitionSectionCollection(Collection<ExhibitionSection> exhibitionsectionCollection) {
        this.exhibitionSectionCollection = exhibitionsectionCollection;
    }

    public void link(Trophy trophy){
        if (updateCycle) return;
        if (trophy==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!trophyCollection.contains(trophy)){
            trophyCollection.add(trophy);
            trophy.link(this);
        }
        updateCycle=false;
    }

    public void unlink(Trophy trophy){
        if (updateCycle) return;
        if (trophy==null) return;//Not the way to clear it..
        updateCycle=true;
        if (trophyCollection.contains(trophy)){
            trophyCollection.remove(trophy);
            trophy.unlink(this);
        }
        updateCycle=false;        
    }    

    @XmlTransient
    public Collection<Trophy> getTrophyCollection() {
        return trophyCollection;
    }

    private void setTrophyCollection(Collection<Trophy> trophyCollection) {
        this.trophyCollection = trophyCollection;
    }

    public void link(ExhibitionExhibitor exhibitionExhibitor){
        if (updateCycle) return;
        if (exhibitionExhibitor==null) return;//Not the way to clear it..
        updateCycle=true;
        if (!exhibitionExhibitorCollection.contains(exhibitionExhibitor)){
            exhibitionExhibitorCollection.add(exhibitionExhibitor);
            exhibitionExhibitor.link(this);
        }
        updateCycle=false;
    }
        
    public void unlink(ExhibitionExhibitor exhibitionExhibitor){
        if (updateCycle) return;
        if (exhibitionExhibitor==null) return;//Not the way to clear it..
        updateCycle=true;
        if (exhibitionExhibitorCollection.contains(exhibitionExhibitor)){
            exhibitionExhibitorCollection.remove(exhibitionExhibitor);
            exhibitionExhibitor.unlink(this);
        }
        updateCycle=false;        
    }

    @XmlTransient
    public Collection<ExhibitionExhibitor> getExhibitionExhibitorCollection() {
        return exhibitionExhibitorCollection;
    }

    private void setExhibitionExhibitorCollection(Collection<ExhibitionExhibitor> exhibitionexhibitorCollection) {
        this.exhibitionExhibitorCollection = exhibitionexhibitorCollection;
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
