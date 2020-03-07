package org.dsacleveland.evictiontracker.model.evictiondata.type;

import java.time.LocalDateTime;

public interface Event {
    EventType getEventType();

    boolean isProSe();

    LocalDateTime getDateTime();

    void setEventType(EventType eventType);

    void setProSe(boolean proSe);

    void setDateTime(LocalDateTime dateTime);
}
