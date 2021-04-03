package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Event;
import org.dsacleveland.evictiontracker.model.evictiondata.type.EventType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Event {
    private EventType eventType;
    private boolean proSe;
    private LocalDateTime dateTime;

    public static Collection<String> toCSV(EventDto event) {
        return Arrays.asList("First Cause", event.dateTime.format(DateTimeFormatter.ofPattern("MM/dd/YYYY kk:mm")));
    }

    // Add filter here when parser is fixed
    public static Collection<String> toCSV(List<EventDto> events) {
        if (events.isEmpty()) {
            return Arrays.asList("", "");
        }
        Optional<EventDto> event = events.stream().findAny();
        return event.map(EventDto::toCSV).orElseGet(() -> Collections.singletonList(""));
    }
}
