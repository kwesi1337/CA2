/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author josephawwal
 */
public class ErrorMessage extends Exception {

    private int code;
    private String message;
    private String stackTrace;
    
    
    /**
     * Creates a new instance of <code>ErrorMessage</code> without detail
     * message.
     */
    public ErrorMessage(Throwable ex, int code, boolean debug) {
        
        this.code = code;
        this.message = ex.getMessage();
        
        if (debug){
            
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            this.stackTrace = sw.toString();
        }
        
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        
        this.message = message;
    }
    
    
    

    /**
     * Constructs an instance of <code>ErrorMessage</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ErrorMessage(String msg) {
        super(msg);
    }
}
