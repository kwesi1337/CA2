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
import javax.persistence.ManyToMany;

/**
 *
 * @author josephawwal
 */
@Entity



public class Hobby implements Serializable {
@Id

private String name;
private String description;

@ManyToMany(mappedBy = "hobbies")
private List<Person> persons = new ArrayList();


public Hobby(){
    
    
}

public Hobby(String name, String description){
    
    this.name = name;
    this.description = description;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    public void addPerson(Person p) {
        persons.add(p);
}


}
