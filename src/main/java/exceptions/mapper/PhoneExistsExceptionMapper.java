/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exceptions.CompanyNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneExistsException;
import java.util.Arrays;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author josephawwal
 */

@Provider
public class PhoneExistsExceptionMapper implements ExceptionMapper<PhoneExistsException> {
    
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    ServletContext context;
    
    @Override
    
    public Response toResponse(PhoneExistsException e){
        
        JsonObject jo = new JsonObject();
        if(Boolean.valueOf(context.getInitParameter("debug"))){
        
        jo.addProperty("StackTrace", Arrays.toString(e.getStackTrace()));
    }
        return Response.status(Response.Status.CONFLICT).entity(jo.toString()).build();
        
    }
}
