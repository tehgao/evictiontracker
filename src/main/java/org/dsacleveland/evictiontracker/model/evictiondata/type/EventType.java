package org.dsacleveland.evictiontracker.model.evictiondata.type;

public enum EventType {

    FC("First Cause Hearing"),
    SC("Second Cause Hearing");

    private String description;

    EventType(String description) {
        this.description = description;
    }
}
