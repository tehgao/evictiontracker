package org.dsacleveland.evictiontracker.service.geo;

import lombok.extern.slf4j.XSlf4j;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Coordinate;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Primary
@Profile("dev")
@XSlf4j
public class MockGeocoderService implements GeocoderService {
    @Override
    public Map<String, String> getAddressComponents(String address) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Getting address components for " + address);

        List<String> neighborhoods = IntStream
                .range(0, 5)
                .mapToObj(index -> "Neighborhood " + index)
                .collect(Collectors.toList());

        Collections.shuffle(neighborhoods);

        String neighborhood = neighborhoods.get(0);

        return Map.of("neighborhood", neighborhood);
    }

    @Override
    public Coordinate getCoordinates(String address) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Getting coordinates for " + address);
        return new Coordinate(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
