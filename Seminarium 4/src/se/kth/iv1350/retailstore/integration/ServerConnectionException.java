package se.kth.iv1350.retailstore.integration;

import java.sql.SQLException;

/**
 * Exception related to server connectivity.
 */
public class ServerConnectionException extends SQLException {
    /**
     * Is created when a problem occur with server connectivity.
     * @param message The message describing the problem.
     */
    public ServerConnectionException(String message){
        super(message);
    }
}
