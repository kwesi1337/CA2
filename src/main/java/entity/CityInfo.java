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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author josephawwal
 */
@Entity

public class CityInfo implements Serializable {

    @Id
    private Long id;
   
    private int zipCode;
    
    private String city;
    
    
    @OneToMany(mappedBy = "cityInfo")
    private List<Address> address = new ArrayList();
    
    

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return address;
    }

    public void setAddresses(List<Address> addresses) {
        this.address = addresses;
    }
    
    

    }


