package com.sida.electivefinalproject.exception;

/**
 * ConnectionException class for ConnectionPool's exceptions
 */
public class ConnectionException extends Exception {
    public ConnectionException() {
        this("ConnectionException was occurred");
    }

    public ConnectionException(String message) {
        super(message);
    }
}
