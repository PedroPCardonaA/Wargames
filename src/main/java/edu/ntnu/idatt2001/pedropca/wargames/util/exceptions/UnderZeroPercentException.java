package edu.ntnu.idatt2001.pedropca.wargames.util.exceptions;

/**
 * Custom exception class that extends from IllegalArgumentException and
 * is thrown when an input that represent a percent is under zero percent.
 *
 * @author Pedro Cardona
 * @version 1.0
 * @since 1.0-SNAPSHOT
 */
public class UnderZeroPercentException extends IllegalArgumentException{
    /**
     * Constructor of the class that call the super constructor of IllegalArgumentException.
     * @param message String - Message of the exception.
     */
    public UnderZeroPercentException(String message){
        super(message);
    }
}
