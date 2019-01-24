package org.trocencheres.dal;

/**
 * @author Kévin Le Devéhat
 */
public class DALException extends Exception {
    public DALException() {
        super();
    }

    public DALException(String message) {
        super(message);
    }

    public DALException(String message, Throwable exception) {
        super(message, exception);
    }

    @Override
    public String getMessage() {
        return "DAL - " + super.getMessage() ;
    }
}
