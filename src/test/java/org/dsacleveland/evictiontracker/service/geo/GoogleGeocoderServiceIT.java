package org.dsacleveland.evictiontracker.service.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Disabled
class GoogleGeocoderServiceIT {

    private GoogleGeocoderService classUnderTest;

    @BeforeEach
    public void setup() {
        this.classUnderTest = new GoogleGeocoderService(System.getProperty("googlemapskey"));
    }

    @Test
    void getAddressComponents() {
        Map<String, String> actual = this.classUnderTest
                .getAddressComponents("13225 shaker square cleveland oh 44120");
    }
}