package org.dsacleveland.evictiontracker.service.geo;

public class GeocoderException extends RuntimeException {
    public GeocoderException(Exception e) {
        super(e);
    }

    public GeocoderException(String s, Exception e) {
        super(s, e);
    }
}
