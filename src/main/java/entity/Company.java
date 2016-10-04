/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;

/**
 *
 * @author josephawwal
 */

@Entity
public class Company extends InfoEntity {
    
    private int cvr;
    private String name;
    private String description;
    private int numEmployees;
    private long marketValue;
    
    public Company(){
        
        
    }
    
    public Company(int cvr, String name, String description, int numEmployees, long marketValue){
        
        this.cvr = cvr;
        this.name = name;
        this.description = description;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }
    
    public int getCvr(){
        
        return cvr;
    }
    
    
    
    public void setCvr(int cvr){
        
        this.cvr = cvr;
    }
    
    
    public String getName(){
        
        return name;
    }
    
    public void setName(String name){
        
        this.name = name;
    }
    
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        
        this.description = description;
    }
    
    public int getNumEmployees(){
        
        return numEmployees;
    }
    
    public void setNumEmployees(int NumEmployees){
        
        this.numEmployees = numEmployees;
    }
    
    public long getMarketValue(){
        
        return marketValue;
    }
    
    public void setMarketValue(long marketValue){
        
        this.marketValue = marketValue;
    }
    
    
}
