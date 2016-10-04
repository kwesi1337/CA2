/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author josephawwal
 */
@Entity
public class Phone implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    
    private String number;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private InfoEntity infoEntity;
    
    public Phone(){
        
        
    }
    
    public Phone(String number, String description){
        
        this.number = number;
        this.description = description;
    }
    
    public String getNumber(){
        
        return number;
        
    }
    
    public void setNumber(String number){
        
        this.number = number;
    }
    
    public String getDescription(){
        
        return description;
        
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public InfoEntity getInfoEntity(){
        
        return infoEntity;
    }
    
    
    public void setInfoEntity(InfoEntity infoEntity){
        
        this.infoEntity = infoEntity;
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
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Phone[ id=" + id + " ]";
    }
    
}
