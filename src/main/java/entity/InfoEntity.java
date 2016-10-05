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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author josephawwal
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class InfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    @OneToMany(mappedBy = "infoEntity", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Phone> phones = new ArrayList();

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private Address address;
    
    
    public InfoEntity(){
        
        
    }   
    
    public InfoEntity(int id, String email){
        
        this.id = id;
        this.email = email;
    }
}