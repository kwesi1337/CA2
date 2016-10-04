/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.Facade;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author Legendary John
 */
@Path("person")
public class RESTperson
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Facade fac;
 
    
    /**
     * Creates a new instance of api
     */
    public RESTperson()
    {
         fac = new Facade( Persistence.createEntityManagerFactory( "REST1PU" ) );
    }

    /**
     * Retrieves representation of an instance of REST.RESTperson
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces("application/json")
    public String getPersons()
    {
        JsonArray result = new JsonArray();
        List<Person> persons = fac.getPersons();
        for (Person person : persons) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("id", person.getId());
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());
            p1.addProperty("address", person.getAddress().getStreet() + " " + person.getAddress().getAdditionalInfo());
            p1.addProperty("zip", person.getAddress().getCityInfoCity().getZip());
            p1.addProperty("city", person.getAddress().getCityInfoCity().getCity());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            p1.add("phonenumbers", phone);

            JsonArray hobby = new JsonArray();
            List<Hobby> hobbies = person.getHobbies();
            for (Hobby h : hobbies) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("name", h.getName());
                p2.addProperty("description", h.getDescription());
                hobby.add(p2);
            }
            p1.add("hobbies", hobby);

            result.add(p1);
        }
        return gson.toJson(result);
    }

    
    @GET
    @Path("complete/{id}")
    @Produces("application/json")
    public String getPerson(@PathParam("id") int id){
        
        Person person = fac.getPerson(id);

        JsonObject p1 = new JsonObject();
        p1.addProperty("id", person.getId());
        p1.addProperty("firstname", person.getFirstName());
        p1.addProperty("lastname", person.getLastName());
        p1.addProperty("email", person.getEmail());
        JsonObject address = new JsonObject();
        address.addProperty("street", person.getAddress().getStreet());
        address.addProperty("additionalinfo", person.getAddress().getAdditionalInfo());

        
        p1.add("address", address);
        p1.addProperty("zip", person.getAddress().getCityInfoCity().getZip());
        p1.addProperty("city", person.getAddress().getCityInfoCity().getCity());

        JsonArray phone = new JsonArray();
        List<Phone> phones = person.getPhones();
        for (Phone p : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p.getNumber());
            p2.addProperty("description", p.getDescription());
            phone.add(p2);
        }
        p1.add("phonenumbers", phone);

        JsonArray hobby = new JsonArray();
        List<Hobby> hobbies = person.getHobbies();
        for (Hobby h : hobbies) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("name", h.getName());
            p2.addProperty("description", h.getDescription());
            hobby.add(p2);
        }
        p1.add("hobbies", hobby);

        return gson.toJson(p1);
        
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id){
        
        Person p = fac.deletePerson(id);
        JsonObject p1 = new JsonObject();
        p1.addProperty("name", p.getFirstName() + " " + p.getLastName());
        
        return gson.toJson(p1);
    }
    
    @GET
    @Path("/contactinfo")
    @Produces("application/json")
    public String getInfo(){
        
        JsonArray info = new JsonArray();
        List<Person> persons = fac.getPersons();
        for (Person person : persons) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("id", person.getId());
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());

            JsonArray phone = new JsonArray();
            Collection<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            p1.add("phonenumbers", phone);

            info.add(p1);
        }
        return gson.toJson(info);
        
    }
    
    @GET
    @Path("contactinfo/{id}")
    @Produces("application/json")
    public String getInfoById(@PathParam("id") int id){
        
        Person person = fac.getPerson(id);
        JsonObject out = new JsonObject();
        out.addProperty("id", person.getId());
        out.addProperty("firstname", person.getFirstName());
        out.addProperty("lastname", person.getLastName());
        out.addProperty("email", person.getEmail());

        JsonArray phone = new JsonArray();
        Collection<Phone> phones = person.getPhones();
        for (Phone p : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p.getNumber());
            p2.addProperty("description", p.getDescription());
            phone.add(p2);
        }
        out.add("phonenumbers", phone);

        return gson.toJson(out);
    }
    
    @POST
    @Path("/complete/add")
    @Consumes("application/json")
    @Produces("application/json")
    public String addPerson(String person){
        
        JsonObject newPerson = new JsonParser().parse(person).getAsJsonObject();
        Person p = new Person();
        p.setFirstName(newPerson.get("firstname").getAsString());
        p.setLastName(newPerson.get("lastname").getAsString());
        p.setEmail(newPerson.get("email").getAsString());
        Address address = new Address();

        address.setAdditionalInfo(newPerson.getAsJsonObject("address").get("additionalinfo").getAsString());
        address.setStreet(newPerson.getAsJsonObject("address").get("street").getAsString());

        CityInfo city = new CityInfo();
        city.setCity(newPerson.get("city").getAsString());
        city.setZip(newPerson.get("zip").getAsString());
        address.setCityInfoCity(city);
        p.setAddress(address);

        JsonArray phonesArr = newPerson.get("phonenumbers").getAsJsonArray();

        for (JsonElement ph : phonesArr) {
            Phone pho = new Phone();
            System.out.println(ph.getAsJsonObject().get("number").getAsString());
            System.out.println(ph.getAsJsonObject().get("description").getAsString());
            pho.setNumber(ph.getAsJsonObject().get("number").getAsString());
            pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
            pho.setInfoEntity(p);
            p.addPhoneNumber(pho);
        }

        JsonArray hobbArr = newPerson.get("hobbies").getAsJsonArray();

        for (JsonElement hob : hobbArr) {
            Hobby ho = new Hobby();
            ho.setDescription(hob.getAsJsonObject().get("description").getAsString());
            ho.setName(hob.getAsJsonObject().get("name").getAsString());
            p.addHobby(ho);
        }

        p = fac.addPerson(p);

        return getPerson(p.getId());  
    }
    /**
     * PUT method for updating or creating an instance of api
     * @param person
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/editperson")
    @Consumes("application/json")
    @Produces("application/json")
    public String editPerson(String person)
    {
        JsonObject newPerson = new JsonParser().parse(person).getAsJsonObject();
        Person p = fac.getPerson(newPerson.get("id").getAsInt());
        p.setFirstName(newPerson.get("firstname").getAsString());
        p.setLastName(newPerson.get("lastname").getAsString());
        p.setEmail(newPerson.get("email").getAsString());
        Address address = p.getAddress();

        address.setAdditionalInfo(newPerson.getAsJsonObject("address").get("additionalinfo").getAsString());
        address.setStreet(newPerson.getAsJsonObject("address").get("street").getAsString());

        CityInfo city = new CityInfo();
        city.setCity(newPerson.get("city").getAsString());
        city.setZip(newPerson.get("zip").getAsString());
        address.setCityInfoCity(city);
        p.setAddress(address);

        JsonArray phonesArr = newPerson.get("phonenumbers").getAsJsonArray();

        JsonElement ph = phonesArr.get(0);
        Phone pho = p.getPhones().get(0);
        pho.setNumber(ph.getAsJsonObject().get("number").getAsString());
        pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
        pho.setInfoEntity(p);
        List<Phone> phones = new ArrayList();
        phones.add(pho);
        p.setPhones(phones);


        JsonArray hobbArr = newPerson.get("hobbies").getAsJsonArray();
        JsonElement hob = hobbArr.get(0);
        Hobby ho = p.getHobbies().get(0);
        ho.setDescription(hob.getAsJsonObject().get("description").getAsString());
        ho.setName(hob.getAsJsonObject().get("name").getAsString());


        p = fac.editPerson(p);

        return getPerson(p.getId());
    }
}
