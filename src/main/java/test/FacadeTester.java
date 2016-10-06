/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.*;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
/**
 *
 * @author olls
 */
public class FacadeTester
{
    public static void main(String[] args)
    {
        
        Facade ft = new Facade( Persistence.createEntityManagerFactory( "CA2pu" ) );
        Person p = new Person( "Work", "plox");
        Address a = new Address();
        
        a.setAdditionalInfo("99");
        a.setStreet("johnarnevej");
        CityInfo cI = new CityInfo();
        cI.setCity("lyngby");
        cI.setZipCode(2650);
        p.setPhoneNum(("11223344"));
        a.setCityInfo(cI);
        List <Hobby> hobbies= new ArrayList();
        hobbies.add(new Hobby("Fusbal", "Brug foden."));
        p.setHobbies(hobbies);
        p.setAddress(a);
        
        Person found = ft.addPerson(p);
        System.out.println("asdasdasd" + found); // Should read out entity.Student something something.
        
    }
}
