package org.dsacleveland.evictiontracker.model.evictiondata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Event;
import org.dsacleveland.evictiontracker.model.evictiondata.type.EventType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Event {
    private EventType eventType;
    private boolean proSe;
    private LocalDateTime dateTime;
}
