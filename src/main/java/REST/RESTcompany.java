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
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exceptions.CompanyNotFoundException;
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
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author LegendaryJohn
 */


@Path("company")
public class RESTcompany
{

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Facade fac;
 
    
    /**
     * Creates a new instance of api
     */
    public RESTcompany()
    {
         fac = new Facade( Persistence.createEntityManagerFactory( "CA2pu" ) );
    }

    /**
     * Retrieves representation of an instance of REST.RESTperson
     * @return an instance of java.lang.String
     */
@GET
    @Path("/complete")
    @Produces("application/json")
    public String getCompanies() {
        JsonArray result = new JsonArray();
        List<Company> companies = fac.getCompanies();
        for (Company c : companies) {
            JsonObject c1 = new JsonObject();
            c1.addProperty("name", c.getName());
            c1.addProperty("description", c.getDescription());
            c1.addProperty("cvr", c.getCvr());
            c1.addProperty("NumEmployees", c.getNumEmployees());
            c1.addProperty("marketValue", c.getMarketValue());

            JsonArray phone = new JsonArray();
            List<Phone> phones = c.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            c1.add("phonenumbers", phone);

            result.add(c1);
        }

        return gson.toJson(result);
    }

    @GET
    @Path("complete/{id}")
    @Produces("application/json")
    public String getCompanyById(@PathParam("id") int id) throws CompanyNotFoundException{

        Company c = fac.getCompany(id);

        JsonObject c1 = new JsonObject();
        c1.addProperty("name", c.getName());
        c1.addProperty("email", c.getEmail());
        c1.addProperty("description", c.getDescription());
        c1.addProperty("cvr", c.getCvr());
        c1.addProperty("NumEmployees", c.getNumEmployees());
        c1.addProperty("marketValue", c.getMarketValue());
        c1.addProperty("address", c.getAddress().getStreet() + " " + c.getAddress().getAdditionalInfo());
        c1.addProperty("zip", c.getAddress().getCityInfo().getZipCode());
        c1.addProperty("city", c.getAddress().getCityInfo().getCity());

        JsonArray phone = new JsonArray();
        List<Phone> phones = c.getPhones();
        for (Phone p : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p.getNumber());
            p2.addProperty("description", p.getDescription());
            phone.add(p2);

            c1.add("phonenumbers", phone);

        }

        return gson.toJson(c1);
    }
}