package edu.ntnu.idatt2001.pedropca.wargames.util.exceptions;

/**
 * Custom exception class that extends from IllegalArgumentException and
 * is thrown when an input is blank.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class BlankInputException extends IllegalArgumentException{
    /**
     * Constructor of the class that call the super constructor of IllegalArgumentException.
     * @param message String - Message of the exception.
     */
    public BlankInputException(String message){
        super(message);
    }
}
