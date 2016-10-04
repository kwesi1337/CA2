/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static entity.InfoEntity_.id;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Size;

/**
 *
 * @author josephawwal
 */
@Entity
@Table(name = "hobby")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h"),
    @NamedQuery(name = "Hobby.findByIdHobby", query = "SELECT h FROM Hobby h WHERE h.idHobby = :idHobby"),
    @NamedQuery(name = "Hobby.findByDescription", query = "SELECT h FROM Hobby h WHERE h.description = :description"),
@NamedQuery(name = "Hobby.findByName", query = "SELECT h FROM Hobby h WHERE h.name = :name")})
    
    
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idHobby")
    private Integer idHobby;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "hobby_has_person", joinColumns = {
       @JoinColumn(name = "Hobby_idHobby", referencedColumnName = "idHobby")}, inverseJoinColumns = {
@JoinColumn(name = "Person_ID", referencedColumnName = "ID")})
    
    @ManyToMany
    private Collection<InfoEntity> infoEntityCollection;
    
    public Hobby(){
        
    }
    
    public Hobby(String description, String name, Collection<InfoEntity> infoEntityCollection)
    {
        this.description = description;
        this.name = name;
        this.infoEntityCollection = infoEntityCollection;
    }  
    
    private Hobby(Integer idHobby){
        
        this.idHobby = idHobby;
    }

    public Integer getIdHobby() {
        return idHobby;
    }

    public void setIdHobby(Integer idHobby) {
        this.idHobby = idHobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<InfoEntity> getInfoEntityCollection() {
        return infoEntityCollection;
    }

    public void setInfoEntityCollection(Collection<InfoEntity> infoEntityCollection) {
        this.infoEntityCollection = infoEntityCollection;
    }
    



    @Override
    public String toString() {
        return "entities.Hobby[ id=" + id + " ]";
    }
    
}
