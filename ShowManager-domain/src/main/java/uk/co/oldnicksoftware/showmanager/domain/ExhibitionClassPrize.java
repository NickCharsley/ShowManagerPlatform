/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.showmanager.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "exhibitionclassprize")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exhibitionclassprize.findAll", query = "SELECT e FROM Exhibitionclassprize e"),
    @NamedQuery(name = "Exhibitionclassprize.findById", query = "SELECT e FROM Exhibitionclassprize e WHERE e.id = :id"),
    @NamedQuery(name = "Exhibitionclassprize.findByPrize", query = "SELECT e FROM Exhibitionclassprize e WHERE e.prize = :prize"),
    @NamedQuery(name = "Exhibitionclassprize.findByPoints", query = "SELECT e FROM Exhibitionclassprize e WHERE e.points = :points")})
public class ExhibitionClassPrize implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Prize")
    private BigDecimal prize;
    @Column(name = "Points")
    private Integer points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exhibitionClassPrizeID")
    private Collection<Sponsorship> sponsorshipCollection;
    @JoinColumn(name = "ExhibitionClassID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ExhibitionClass exhibitionClassID;
    @JoinColumn(name = "ExhibitionExhibitorID", referencedColumnName = "ID")
    @ManyToOne
    private ExhibitionExhibitor exhibitionExhibitorID;
    @JoinColumn(name = "PrizeID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Prize prizeID;

    public ExhibitionClassPrize() {
    }

    public ExhibitionClassPrize(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @XmlTransient
    public Collection<Sponsorship> getSponsorshipCollection() {
        return sponsorshipCollection;
    }

    public void setSponsorshipCollection(Collection<Sponsorship> sponsorshipCollection) {
        this.sponsorshipCollection = sponsorshipCollection;
    }

    public ExhibitionClass getExhibitionClassID() {
        return exhibitionClassID;
    }

    private boolean updateCycle = false;
    
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

    public ExhibitionExhibitor getExhibitionExhibitorID() {
        return exhibitionExhibitorID;
    }

    public void setExhibitionExhibitorID(ExhibitionExhibitor exhibitionExhibitorID) {
        this.exhibitionExhibitorID = exhibitionExhibitorID;
    }

    public Prize getPrizeID() {
        return prizeID;
    }

    public void setPrizeID(Prize prizeID) {
        this.prizeID = prizeID;
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
        if (!(object instanceof ExhibitionClassPrize)) {
            return false;
        }
        ExhibitionClassPrize other = (ExhibitionClassPrize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Exhibitionclassprize[ id=" + id + " ]";
    }
    
}
