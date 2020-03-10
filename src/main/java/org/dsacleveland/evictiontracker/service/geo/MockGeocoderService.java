package org.dsacleveland.evictiontracker.service.geo;

import org.dsacleveland.evictiontracker.model.evictiondata.type.Coordinate;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Primary
@Profile("dev")
public class MockGeocoderService implements GeocoderService {
    @Override
    public Map<String, String> getAddressComponents(String address) {
        return Map.of("neighborhood", "asdf");
    }

    @Override
    public Coordinate getCoordinates(String address) {
        return new Coordinate(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
