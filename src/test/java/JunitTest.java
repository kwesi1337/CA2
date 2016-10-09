/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Person;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.persistence.*;
import facade.Facade;
import entity.*;
/**
 *
 * @author jakob
 */
public class JunitTest
{
    private static EntityManagerFactory emf;
    private static Facade facade;
    
    
    public JunitTest()
    {
        
        
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    @Test
    public void testAddAndGetPerson() {
       emf = Persistence.createEntityManagerFactory("CA2pu");
        facade= new Facade(emf);
        
        int id =1; 
        
        facade.addPerson(new Person("john", "arne"));
        
      
        List<Person> l = facade.getPersons();
        int foundId=0;
        for (Person person : l) {
            if (person.getFirstName().equalsIgnoreCase("John") && person.getLastName().equalsIgnoreCase("arne"))
            {
                foundId = 1;
                //System.out.println("JAJA");
            }
        }
        assertTrue(id==foundId);
    }
    @Test
    public void testDeletePerson()
    {
        emf = Persistence.createEntityManagerFactory("CA2pu");
        facade= new Facade(emf);
        Person p = new Person("john", "arne");
        
        facade.addPerson(p);
        assertTrue(p.getFirstName().equalsIgnoreCase( facade.getPerson(p.getId()).getFirstName()));
        facade.deletePerson(p.getId());
        Person p2 = facade.getPerson(p.getId());
        assertTrue(p2 == null);
        
        
        
    }
    
    
    @Test
    public void testHobbies()
    {
        boolean test=false;
        Person p = new Person("john", "arne");
        Hobby h = new Hobby("johnsport", "john dyrker det yo");
        
        h.addPerson(p);
        
        List<Person> ps= h.getPersons();
        if(ps.contains(p))
        {
            test=true;
        }
        assertTrue(test);
        
        
        
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}