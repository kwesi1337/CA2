/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Company;
import entity.Phone;
import exceptions.CompanyNotFoundException;
import facade.Facade;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

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
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanies() {
        JsonArray result = new JsonArray();
        List<Company> companies = fac.getCompanies();
        for (Company c : companies) {
            JsonObject obj = new JsonObject();
            obj.addProperty("name", c.getName());
            obj.addProperty("description", c.getDescription());
            obj.addProperty("cvr", c.getCvr());
            obj.addProperty("NumEmployees", c.getNumEmployees());
            obj.addProperty("marketValue", c.getMarketValue());

            JsonArray phone = new JsonArray();
            List<Phone> phones = c.getPhones();
            for (Phone p1 : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p1.getNumber());
                p2.addProperty("description", p1.getDescription());
                phone.add(p2);
            }
            obj.add("phonenumbers", phone);

            result.add(obj);
        }

        return gson.toJson(result);
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyById(@PathParam("id") int id) throws CompanyNotFoundException{

        Company c = fac.getCompanyById(id);

        JsonObject obj = new JsonObject();
        obj.addProperty("name", c.getName());
        obj.addProperty("email", c.getEmail());
        obj.addProperty("description", c.getDescription());
        obj.addProperty("cvr", c.getCvr());
        obj.addProperty("NumEmployees", c.getNumEmployees());
        obj.addProperty("marketValue", c.getMarketValue());
        obj.addProperty("address", c.getAddress().getStreet() + " " + c.getAddress().getAdditionalInfo());
        obj.addProperty("zip", c.getAddress().getCityInfo().getZipCode());
        obj.addProperty("city", c.getAddress().getCityInfo().getCity());

        JsonArray phone = new JsonArray();
        List<Phone> phones = c.getPhones();
        for (Phone p1 : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p1.getNumber());
            p2.addProperty("description", p1.getDescription());
            phone.add(p2);

            obj.add("phonenumbers", phone);

        }

        return gson.toJson(obj);
    }
    
    @GET
    @Path("phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyByPhone(@PathParam("number") String number){
        
        Company c = fac.getCompanyByPhone(number);
        
        JsonObject obj = new JsonObject();
        
        obj.addProperty("cvr", c.getCvr());
        obj.addProperty("name", c.getName());
        obj.addProperty("email", c.getEmail()); 
        obj.addProperty("address", c.getAddress().getStreet() + " " + c.getAddress().getAdditionalInfo());
        obj.addProperty("zip", c.getAddress().getCityInfo().getZipCode());
        obj.addProperty("city", c.getAddress().getCityInfo().getCity());
        
        JsonArray phone = new JsonArray();
        List<Phone> phones = c.getPhones();
        for (Phone p1 : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p1.getNumber());
            p2.addProperty("description", p1.getDescription());
            phone.add(p2);

            obj.add("phonenumbers", phone);

        }
        
        return gson.toJson(obj);
    }
    
    @GET
    @Path("cvr/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyByCvr(@PathParam("number") int number){
        
        Company c = fac.getCompanyByCvr(number);
        
        JsonObject obj = new JsonObject();
        
        obj.addProperty("cvr", c.getCvr());
        obj.addProperty("name", c.getName());
        obj.addProperty("email", c.getEmail());
        obj.addProperty("address", c.getAddress().getStreet() + " " + c.getAddress().getAdditionalInfo());
        obj.addProperty("zip", c.getAddress().getCityInfo().getZipCode());
        obj.addProperty("city", c.getAddress().getCityInfo().getCity());
        
        JsonArray phone = new JsonArray();
        List<Phone> phones = c.getPhones();
        for (Phone p1 : phones) {
            JsonObject p2 = new JsonObject();
            p2.addProperty("number", p1.getNumber());
            p2.addProperty("description", p1.getDescription());
            phone.add(p2);

            obj.add("phonenumbers", phone);

        }
        
        return gson.toJson(obj);
    }
    
    @GET
    @Path("employees/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompaniesByEmp(@PathParam("number") int number){
        
        List<Company> c1 = fac.getCompaniesByEmp(number);
        List<JsonObject> jsonList = new ArrayList();
        
        for (Company c2 : c1) {
            JsonObject job = new JsonObject();
            job.addProperty("id", c2.getId());
            job.addProperty("name", c2.getName());
            job.addProperty("email", c2.getEmail());
            
            jsonList.add(job);
        }
        return gson.toJson(jsonList);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCompany(String content){
        
        Company c = gson.fromJson(content, Company.class);
        Company addCompany = fac.addCompany(c);
        
        return gson.toJson(addCompany);
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCompany(@PathParam("id") int id){
        
        Company c = fac.deleteCompany(id);
        JsonObject obj = new JsonObject();
        obj.addProperty("name", c.getName());
        
        return gson.toJson(obj);
    }
    
}