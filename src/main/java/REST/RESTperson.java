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
import exceptions.PersonNotFoundException;
import facade.Facade;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.util.Collection;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Legendary John
 */
@Path("person")
public class RESTperson
{

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Facade fac;

    /**
     * Creates a new instance of api
     */
    public RESTperson()
    {
        fac = new Facade(Persistence.createEntityManagerFactory("CA2pu"));
    }

    /**
     * Retrieves representation of an instance of REST.RESTperson
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons()
    {
        JsonArray result = new JsonArray();
        List<Person> persons = fac.getPersons();
        for (Person person : persons)
        {
            JsonObject p1 = new JsonObject();
            p1.addProperty("id", person.getId());
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());
            //p1.addProperty("address", person.getAddress().getStreet() + " " + person.getAddress().getAdditionalInfo());
            JsonObject address = new JsonObject();
            address.addProperty("street", person.getAddress().getStreet());
            address.addProperty("additionalinfo", person.getAddress().getAdditionalInfo());

            p1.add("address", address);

            p1.addProperty("zip", person.getAddress().getCityInfo().getZipCode());
            p1.addProperty("city", person.getAddress().getCityInfo().getCity());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones)
            {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            p1.add("phonenumbers", phone);

            JsonArray hobby = new JsonArray();
            List<Hobby> hobbies = person.getHobbies();
            for (Hobby h : hobbies)
            {
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

    /**
     *
     * @param id
     * @return
     * @throws PersonNotFoundException
     */
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException
    {

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
        p1.addProperty("zip", person.getAddress().getCityInfo().getZipCode());
        p1.addProperty("city", person.getAddress().getCityInfo().getCity());

        JsonArray phone = new JsonArray();
        List<Phone> phones = person.getPhones();
        for (Phone p : phones)
        {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p.getNumber());
            p2.addProperty("description", p.getDescription());
            phone.add(p2);
        }
        p1.add("phonenumbers", phone);

        JsonArray hobby = new JsonArray();
        List<Hobby> hobbies = person.getHobbies();
        for (Hobby hobb : hobbies)
        {
            JsonObject p2 = new JsonObject();
            p2.addProperty("name", hobb.getName());
            p2.addProperty("description", hobb.getDescription());
            hobby.add(p2);
        }
        p1.add("hobbies", hobby);

        return gson.toJson(p1);

    }

    /**
     *
     * @param id
     * @return
     * @throws PersonNotFoundException
     */
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException
    {

        Person p = fac.deletePerson(id);
        JsonObject p1 = new JsonObject();
        p1.addProperty("name", p.getFirstName() + " " + p.getLastName());

        return gson.toJson(p1);
    }

    /**
     *
     * @return
     */
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo()
    {

        JsonArray info = new JsonArray();
        List<Person> persons = fac.getPersons();
        for (Person person : persons)
        {
            JsonObject p1 = new JsonObject();
            p1.addProperty("id", person.getId());
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());

            JsonArray phone = new JsonArray();
            Collection<Phone> phones = person.getPhones();
            for (Phone p : phones)
            {
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

    /**
     *
     * @param id
     * @return
     * @throws PersonNotFoundException
     */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfoById(@PathParam("id") int id) throws PersonNotFoundException
    {

        Person person = fac.getPerson(id);
        JsonObject obj = new JsonObject();
        obj.addProperty("id", person.getId());
        obj.addProperty("firstname", person.getFirstName());
        obj.addProperty("lastname", person.getLastName());
        obj.addProperty("email", person.getEmail());

        JsonArray phone = new JsonArray();
        Collection<Phone> phones = person.getPhones();
        for (Phone p : phones)
        {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p.getNumber());
            p2.addProperty("description", p.getDescription());
            phone.add(p2);
        }
        obj.add("phonenumbers", phone);

        return gson.toJson(obj);
    }

    /**
     *
     * @param person
     * @return
     * @throws PersonNotFoundException
     */
    @POST
    @Path("/complete/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String person) throws PersonNotFoundException
    {

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
        city.setZipCode(newPerson.get("zip").getAsInt());
        address.setCityInfo(city);
        p.setAddress(address);

        JsonArray phonesArr = newPerson.get("phonenumbers").getAsJsonArray();

        for (JsonElement ph : phonesArr)
        {
            Phone pho = new Phone();
            System.out.println(ph.getAsJsonObject().get("number").getAsString());
            System.out.println(ph.getAsJsonObject().get("description").getAsString());
            pho.setNumber(ph.getAsJsonObject().get("number").getAsString());
            pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
            pho.setInfoEntity(p);
            //         p.addPhoneNumber(pho);
        }

        JsonArray hobbArr = newPerson.get("hobbies").getAsJsonArray();

        for (JsonElement hobb : hobbArr)
        {
            Hobby hob = new Hobby();
            hob.setDescription(hobb.getAsJsonObject().get("description").getAsString());
            hob.setName(hobb.getAsJsonObject().get("name").getAsString());
            // p.addHobby(ho);
        }

        p = fac.addPerson(p);

        return getPerson(p.getId());
    }

    /**
     * PUT method for updating or creating an instance of api
     *
     * @param person
     * @return an HTTP response with content of the updated or created resource.
     * @throws exceptions.PersonNotFoundException
     */
    @PUT
    @Path("/editperson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(String person) throws PersonNotFoundException
    {
        JsonObject editPerson = new JsonParser().parse(person).getAsJsonObject();
        Person p = fac.getPerson(editPerson.get("id").getAsInt());
        p.setFirstName(editPerson.get("firstname").getAsString());
        p.setLastName(editPerson.get("lastname").getAsString());
        p.setEmail(editPerson.get("email").getAsString());
        Address address = p.getAddress();

        address.setAdditionalInfo(editPerson.getAsJsonObject("address").get("additionalinfo").getAsString());
        address.setStreet(editPerson.getAsJsonObject("address").get("street").getAsString());

        CityInfo city = new CityInfo();
        city.setCity(editPerson.get("city").getAsString());
        city.setZipCode(editPerson.get("zip").getAsInt());
        address.setCityInfo(city);
        p.setAddress(address);

        JsonArray phonesArr = editPerson.get("phonenumbers").getAsJsonArray();
        
        for (JsonElement ph : phonesArr)
        {
            Phone pho = new Phone();
            System.out.println(ph.getAsJsonObject().get("number").getAsString());
            System.out.println(ph.getAsJsonObject().get("description").getAsString());
            pho.setNumber(ph.getAsJsonObject().get("number").getAsString());
            pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
            pho.setInfoEntity(p);
            //         p.addPhoneNumber(pho);
        }

        JsonArray hobbArr = editPerson.get("hobbies").getAsJsonArray();
        
          for (JsonElement hobb : hobbArr)
        {
            Hobby hob = new Hobby();
            hob.setDescription(hobb.getAsJsonObject().get("description").getAsString());
            hob.setName(hobb.getAsJsonObject().get("name").getAsString());
            // p.addHobby(ho);
        }

        p = fac.editPerson(p);

        return getPerson(p.getId());
    }

    public static String getJSONFromPerson(Person person)
    {
        JsonObject joPerson = new JsonObject();
        joPerson.addProperty("id", person.getId());
        joPerson.addProperty("firstName", person.getFirstName());
        joPerson.addProperty("lastName", person.getLastName());
        joPerson.addProperty("email", person.getEmail());
        joPerson.addProperty("street", person.getAddress().getStreet());
        joPerson.addProperty("additionalInfo", person.getAddress().getAdditionalInfo());
//        joPerson.addProperty("city", person.getAddress().getCityInfo().getCity());
        joPerson.addProperty("zipCode", person.getAddress().getCityInfo().getZipCode());

        JsonArray jaPhone = new JsonArray();
        for (Phone phone : person.getPhones())
        {

            JsonObject jso = new JsonObject();
            jso.addProperty("number", phone.getNumber());
            jso.addProperty("description", phone.getDescription());
            jaPhone.add(jso);
        }

        joPerson.add("phones", jaPhone);
        JsonArray jaHobby = new JsonArray();
        for (Hobby hobby : person.getHobbies())
        {

            JsonObject jso = new JsonObject();
            jso.addProperty("name", hobby.getName());
            jso.addProperty("description", hobby.getDescription());
            jaHobby.add(jso);
        }
        joPerson.add("hobbies", jaHobby);
        System.out.println("getJsonFromPerson:\n" + joPerson.toString());
        return gson.toJson(joPerson);
    }
}
