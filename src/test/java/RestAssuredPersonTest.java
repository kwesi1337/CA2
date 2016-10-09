/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import REST.RESTperson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.parsing.Parser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.Facade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 *
 * @author josephawwal
 */
public class RestAssuredPersonTest
{

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf;
    EntityManager em;
    private static Facade facade;

    public RestAssuredPersonTest()
    {
        emf = Persistence.createEntityManagerFactory("CA2pu");
        em = emf.createEntityManager();
        facade = new Facade(emf);

    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/CA2";
        RestAssured.defaultParser = Parser.JSON;

    }

    @Before
    public void setUp()
    {
        em = emf.createEntityManager();
        em.getTransaction().begin();

    }

    
    @Test
    public void serverIsRunning() {
        given().when().get("http://localhost:8080/CA2/").then().statusCode(200);
    }
    
    
    @Test
    public void postGetDeletePerson()
    {
        Person p = new Person("Kurt", "Wonnegut");
        Person newPerson
                = given()
                .contentType("application/json")
                .body(p)
                .when().post("/api/person")
                .as(Person.class);

        assertNotNull(newPerson.getId());

        given()
                .contentType(ContentType.JSON)
                .when().get("/api/person/" + newPerson.getId()).then()
                .body("id", notNullValue())
                .body("firstName", equalTo("Kurt"));

        given()
                .contentType(ContentType.JSON)
                .when().delete("/api/person/" + newPerson.getId()).then()
                .body("firstName", equalTo("Kurt"));
    }

//    @Test
//    public void serverIsRunning()
//    {
//        given().
//                when().get().
//                then().statusCode(200);
//    }

    @Test
    public void createPerson()
    {

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
