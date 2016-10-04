/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author josephawwal
 */
@Entity
@Table(name = "infoentity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Infoentity.findAll", query = "SELECT i FROM Infoentity i"),
    @NamedQuery(name = "Infoentity.findById", query = "SELECT i FROM Infoentity i WHERE i.id = :id"),
    @NamedQuery(name = "Infoentity.findByDtype", query = "SELECT i FROM Infoentity i WHERE i.dtype = :dtype"),
    @NamedQuery(name = "Infoentity.findByCompanyID", query = "SELECT i FROM Infoentity i WHERE i.companyID = :companyID"),
    @NamedQuery(name = "Infoentity.findByEmail", query = "SELECT i FROM Infoentity i WHERE i.email = :email"),
@NamedQuery(name = "Infoentity.findByPersonID", query = "SELECT i FROM Infoentity i WHERE i.personID = :personID")})
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 31)
    @Column(name = "DTYPE")
    private String dType;
    @Column(name = "Company_ID")
    private Integer companyId;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "Person_ID")
    private Integer personId;
    @ManyToMany(mappedBy = "infoEntityCollection")
    private List<Hobby> hobbies;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "infoEntity")
    private Person person;
    @OneToMany(mappedBy = "infoEntityIdCollection")
    private List<Phone> phones;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "infoEntity")
    private Company company;
    @JoinColumn(name = "address_isAddress", referencedColumnName = "idAddress")
    @ManyToOne(optional = false)
    private Address address;

    public void setHobby(List<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

    public List<Hobby> getHobbies()
    {
        return hobbies;
    }
    
     public void addHobby(Hobby hobby){
        hobbies.add(hobby);
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhones(List<Phone> phones)
    {
        this.phones = phones;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Integer getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public List<Phone> getPhones()
    {
        return phones;
    }

    public Address getAddress()
    {
        return address;
    }
  
    public void addPhoneNumber(Phone phone) {
        phones.add(phone);
    }
   
    
    public InfoEntity(){
        
        
    }
    
    public InfoEntity(Integer id){
        
        this.id = id;
    }
    
    public InfoEntity(String dType, Integer companyId, String email,
            Integer personId, List<Hobby> hobbies,
            Person person, List<Phone> phones, 
            Company company, Address address){
        
        this.dType = dType;
        this.companyId = companyId;
        this.email = email;
        this.personId = personId;
        this.hobbies = hobbies;
        this.person = person;
        this.phones = phones;
        this.company = company;
        this.address = address;
        
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
        if (!(object instanceof InfoEntity)) {
            return false;
        }
        InfoEntity other = (InfoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InfoEntity[ id=" + id + " ]";
    }
    
    
    
}
