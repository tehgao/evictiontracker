package org.dsacleveland.evictiontracker.model.evictiondata.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
