/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author josephawwal
 */
@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    private int zipCode;
    private String city;
    
    
    @OneToMany(mappedBy = "city")
    private List<Address> addresses = new ArrayList();
    
    public CityInfo(){
        
        
    }
    
    public CityInfo(int zipCode, String city){
        
        
    }
    
    public int getZipCode(){
        
        return zipCode;
    }
    
    public void setZipCode(int zipCode){
        
        this.zipCode = zipCode;
    }
    
    public String getCity(){
        
        return city;
    }
    
    public void setCity(String city){
        
        this.city = city;
    }
    
    public List<Address> getAddresses(){
        
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses){
        
        this.addresses = addresses;
    }
    
    public void addAddress(Address a){
        
        addresses.add(a);
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
