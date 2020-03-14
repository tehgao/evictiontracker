package org.dsacleveland.evictiontracker.config.exception;

public class EvictionTrackerAppException extends RuntimeException {
    public EvictionTrackerAppException() {
        super("Unspecified eviction tracker error");
    }

    public EvictionTrackerAppException(String message) {
        super(message);
    }

    public EvictionTrackerAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvictionTrackerAppException(Throwable cause) {
        super(cause);
    }
}
