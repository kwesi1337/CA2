/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author josephawwal
 */
public class PhoneExistsException extends Exception {

    /**
     * Creates a new instance of <code>PhoneExistsException</code> without
     * detail message.
     */
    public PhoneExistsException() {
        super("Phone nr already exists.");
    }

    /**
     * Constructs an instance of <code>PhoneExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PhoneExistsException(String msg) {
        super(msg);
    }
}
