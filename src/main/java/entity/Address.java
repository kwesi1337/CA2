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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author josephawwal
 */
//@Entity
//@Table(name = "address")
//@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
//    @NamedQuery(name = "Address.findByIdAddress", query = "SELECT a FROM Address a WHERE a.idAddress = :idAddress"),
//    @NamedQuery(name = "Address.findByAdditionalInfo", query = "SELECT a FROM Address a WHERE a.additionalInfo = :additionalInfo"),
//    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
//})
@Entity
public class Address implements Serializable {

    @Id
    private String street;
    
 
   

    private String additionalInfo;
    
    

    @OneToMany(mappedBy = "address")
    private List<InfoEntity> infoEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private CityInfo city;


    public Address() {

    }

    public Address(String street, String additionalInfo, CityInfo city){
        
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.city = city;
    }
    public Address(String street, String additionalInfo, CityInfo cityInfoCity, List<InfoEntity> infoEntityList) {

        this.street = street;
        this.additionalInfo = additionalInfo;

    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<InfoEntity> getInfoEntities() {
        return infoEntities;
    }

    public void setInfoEntities(List<InfoEntity> infoEntities) {
        this.infoEntities = infoEntities;
    }

    public CityInfo getCityInfo() {
        return city;
    }

    public void setCityInfo(CityInfo city) {
        this.city = city;
    }

  


}
