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

    
public class Company implements Serializable {

@Id
private int zipCode;

private String cityInfo;

@OneToMany(mappedBy = "cityInfo")
private List<Address> addresses = new ArrayList();

    
    public Company(){
        
        
    }


    
}
