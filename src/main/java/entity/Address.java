/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author josephawwal
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByIdAddress", query = "SELECT a FROM Address a WHERE a.idAddress = :idAddress"),
    @NamedQuery(name = "Address.findByAdditionalInfo", query = "SELECT a FROM Address a WHERE a.additionalInfo = :additionalInfo"),
    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")})

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAddress")
    private Integer idAddress;
    @Size(max = 255)
    @Column(name = "additionalInfo")
    private String additionalInfo;
    @Size(max = 255)
    @Column(name = "street")
    private String street;
    @JoinColumn(name = "Cityinfo_idCityinfo", referencedColumnName = "idCityinfo")
    @ManyToOne
    private CityInfo cityInfoCity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressIdAddress")
    private Collection<InfoEntity> infoEntityCollection;
    

    public Address() {

    }

    public Address(String street, String additionalInfo, CityInfo cityInfoCity, Collection<InfoEntity> infoEntityCollection){
        

        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfoCity = cityInfoCity;
        this.infoEntityCollection = infoEntityCollection;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public CityInfo getCityInfoCity() {
        return cityInfoCity;
    }

    public void setCityInfoCity(CityInfo cityInfoCity) {
        this.cityInfoCity = cityInfoCity;
    }

    public Collection<InfoEntity> getInfoEntityCollection() {
        return infoEntityCollection;
    }

    public void setInfoEntityCollection(Collection<InfoEntity> infoEntityCollection) {
        this.infoEntityCollection = infoEntityCollection;
    }


    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Address[ id=" + id + " ]";
    }

}
