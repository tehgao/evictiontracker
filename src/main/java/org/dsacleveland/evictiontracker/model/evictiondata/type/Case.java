package org.dsacleveland.evictiontracker.model.evictiondata.type;

import java.time.LocalDate;
import java.util.List;

public interface Case<P extends Party, E extends Event> {
    String getCaseNumber();

    LocalDate getFileDate();

    List<? extends P> getPlaintiffs();

    List<? extends P> getDefendants();

    List<? extends E> getEvents();

    void setCaseNumber(String caseNumber);

    void setFileDate(LocalDate fileDate);

    void setPlaintiffs(List<P> plaintiffs);

    void setDefendants(List<P> defendants);

    void setEvents(List<E> events);
}
