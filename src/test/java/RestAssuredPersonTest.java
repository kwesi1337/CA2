/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import REST.RESTperson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.parsing.Parser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import org.junit.BeforeClass;
/**
 *
 * @author josephawwal
 */
public class RestAssuredPersonTest {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public RestAssuredPersonTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        baseURI = "http://localhost:9000";
        defaultParser = Parser.JSON;
        basePath = "/CA2/api/person";
        
    }
    
 
    
    @Test
    public void createPerson(){
        
        Person p = new Person("Test", "Test123");
        p.setEmail("tobias.cbs@gmail.com");
        Phone phone1 = new Phone("123456789", "iphone");
        Phone phone2 = new Phone("987654321", "Samsung");
        p.addPhone(phone1);
        p.addPhone(phone2);
        CityInfo cityInfo = new CityInfo(2000, "Frederiksberg");
        Address address = new Address("Janusvej 4", "1tv", cityInfo);
        p.setAddress(address);
        
        
        Hobby hob1 = new Hobby("gaming", "gaming ftw");
        Hobby hob2 = new Hobby("Spisning", "spisning ftw");
        
        p.addHobby(hob1);
        p.addHobby(hob2);
        
       given().
               contentType(JSON).
               body(RESTperson.getJSONFromPerson(p)).
               when().
               post().
               then().
               contentType(JSON).
               statusCode(201).body("firstName", equalTo(p.getFirstName())).
               body("lastName", equalTo(p.getLastName())).
               body("hobbies.name", hasItems(p.getHobbies().get(0).getName()));
       
               
        
    }
 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
