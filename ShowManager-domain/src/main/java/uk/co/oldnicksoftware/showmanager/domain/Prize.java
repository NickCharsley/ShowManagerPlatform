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
@Table(name = "prize")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prize.findAll", query = "SELECT p FROM Prize p"),
    @NamedQuery(name = "Prize.findById", query = "SELECT p FROM Prize p WHERE p.id = :id"),
    @NamedQuery(name = "Prize.findByName", query = "SELECT p FROM Prize p WHERE p.name = :name"),
    @NamedQuery(name = "Prize.findByPrize", query = "SELECT p FROM Prize p WHERE p.prize = :prize"),
    @NamedQuery(name = "Prize.findByPoints", query = "SELECT p FROM Prize p WHERE p.points = :points")})
public class Prize implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Prize")
    private BigDecimal prize;
    @Column(name = "Points")
    private Integer points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prizeID")
    private Collection<Exhibitionclassprize> exhibitionclassprizeCollection;

    public Prize() {
    }

    public Prize(Integer id) {
        this.id = id;
    }

    public Prize(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    public Collection<Exhibitionclassprize> getExhibitionclassprizeCollection() {
        return exhibitionclassprizeCollection;
    }

    public void setExhibitionclassprizeCollection(Collection<Exhibitionclassprize> exhibitionclassprizeCollection) {
        this.exhibitionclassprizeCollection = exhibitionclassprizeCollection;
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
        if (!(object instanceof Prize)) {
            return false;
        }
        Prize other = (Prize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uk.co.oldnicksoftware.showmanager.Prize[ id=" + id + " ]";
    }
    
}
