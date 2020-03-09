package org.dsacleveland.evictiontracker.service.geo;

import org.dsacleveland.evictiontracker.model.evictiondata.type.Coordinate;

import java.util.Map;

public interface GeocoderService {
    Map<String, String> getAddressComponents(String address);

    Coordinate getCoordinates(String address);
}
