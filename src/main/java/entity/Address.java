/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author josephawwal
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    private String street;
    
    private String additionalInfo;
    
    @OneToMany(mappedBy = "address")
    private List<InfoEntity> infoEntities = new ArrayList();
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private CityInfo city;
    
    
    public Address(){
        
        
    }
    
    
    public Address(String street,  String additionalInfo){
        
        this.street = street;
        this.additionalInfo = additionalInfo;
    }
    
    
    public void setInfoEntities(List<InfoEntity> infoEntities){
        
        this.infoEntities = infoEntities;
        
    }
    
    public String getStreet(){
        
        return street;
    }
    
    public void setStreet(String street){
        
        this.street = street;
    }
    
    public String getAdditionalInfo(){
        
        return additionalInfo;
    }
    
    public void setAdditionalInfo(String additionalInfo){
        
        this.additionalInfo = additionalInfo;
    }
    
    public List<InfoEntity> getInfoEntities(){
        
        return infoEntities;
    }
    
    public void addInfoEntity(InfoEntity ie){
        
       // ie.setAddress(this);
        infoEntities.add(ie);
    }
    
    public CityInfo getCityInfo(){
        
        return city;
    }
    
    
    public void setCityInfo(CityInfo city){
        
        city.addAddress(this);
        this.city = city;
        
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
