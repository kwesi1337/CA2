/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author josephawwal
 */
@Entity
@Table(name = "CITYINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CityInfo.findAll", query = "SELECT c FROM CityInfo c"),
    @NamedQuery(name = "CityInfo.findByIdCityinfo", query = "SELECT c FROM CityInfo c WHERE c.idCityInfo = :idCityInfo"),
    @NamedQuery(name = "CityInfo.findByCity", query = "SELECT c FROM CityInfo c WHERE c.city = :city"),
    @NamedQuery(name = "CityInfo.findByZip", query = "SELECT c FROM CityInfo c WHERE c.zip = :zip")})

public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCityInfo")
    private Integer idCityInfo;
    @Size(max = 255)
    @Column(name = "City")
    private String city;
    @Column(name = "Zip")
    private String zip;
    @OneToMany(mappedBy = "cityInfoCity")
    private List<Address> address;
    
   

    public CityInfo() {

    }

    public CityInfo(String zip, String city, List<Address> address) {
        
        this.zip = zip;
        this.city = city;
        this.address = address;
        
    }

    public Integer getIdCityInfo() {
        return idCityInfo;
    }

    public void setIdCityInfo(Integer idCityInfo) {
        this.idCityInfo = idCityInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @XmlTransient
    public List<Address> getAddressCollection() {
        return address;
    }

    public void setAddressCollection(List<Address> addressCollection) {
        this.address = addressCollection;
    }
  


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
        if (!(object instanceof CityInfo)) {
            return false;
        }
        CityInfo other = (CityInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CityInfo[ id=" + id + " ]";
    }

}
