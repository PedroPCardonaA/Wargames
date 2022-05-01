package edu.ntnu.idatt2001.pedropca.wargames.util.Exceptions;

/**
 * Custom exception class that extends from IllegalArgumentException and
 * is thrown when an input that suppose to be an integer is not an integer.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class NotIntegerException extends IllegalArgumentException{
    /**
     * Constructor of the class that call the super constructor of IllegalArgumentException.
     * @param message String - Message of the exception.
     */
    public NotIntegerException(String message){
        super(message);
    }
}

