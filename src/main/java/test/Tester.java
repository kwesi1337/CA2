/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Person;
import facade.Facade;

/**
 *
 * @author josephawwal
 */
public class Tester {
    
    
    public static void main(String[] args) {
        Facade fp = new Facade();
        Person p = new Person();
        
        
        
        fp.addPerson(p);
        
        System.out.println("Hi" + p);
        
    }
}
