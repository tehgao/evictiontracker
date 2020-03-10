package org.dsacleveland.evictiontracker.model.evictiondata.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    @Column(scale = 16)
    private BigDecimal latitude;

    @Column(scale = 16)
    private BigDecimal longitude;
}
