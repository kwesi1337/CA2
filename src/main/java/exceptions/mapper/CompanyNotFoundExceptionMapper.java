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
public class CompanyNotFoundExceptionMapper implements ExceptionMapper<CompanyNotFoundException>{
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @Context
    
    ServletContext context;
    
    @Override
    
    public Response toResponse(CompanyNotFoundException e){
        
        JsonObject jo = new JsonObject();
        if (Boolean.valueOf(context.getInitParameter("debug"))){
            
            jo.addProperty("StackTrace", Arrays.toString(e.getStackTrace()));
        }
        
        jo.addProperty("Message", e.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
        
    }
}
