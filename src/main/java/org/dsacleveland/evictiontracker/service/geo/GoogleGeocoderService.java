package org.dsacleveland.evictiontracker.service.geo;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.dsacleveland.evictiontracker.model.evictiondata.type.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Profile("!local-dev")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GoogleGeocoderService implements GeocoderService {
    private GeoApiContext geoApiContext;
    private Map<String, TimeCachedResult> cache;

    @Autowired
    public GoogleGeocoderService(@Value("${com.google.apikey}") String apiKey) {
        // please for the love of god be judicious with this API
        this.geoApiContext = new GeoApiContext.Builder().apiKey(apiKey).build();

        this.cache = new HashMap<>();
    }

    @Override
    public Map<String, String> getAddressComponents(String address) {
        GeocodingResult[] results = this.getResults(address);

        return Arrays.stream(results[0].addressComponents)
                     .collect(Collectors.toMap(ac -> ac.types[0].toString(), ac -> ac.longName));
    }

    @Override
    public Coordinate getCoordinates(String address) {
        LatLng latLng = this.getResults(address)[0].geometry.location;
        return new Coordinate(BigDecimal.valueOf(latLng.lat), BigDecimal.valueOf(latLng.lng));
    }

    private synchronized GeocodingResult[] getResults(String address) {
        GeocodingResult[] results;

        if (this.cache.containsKey(address) &&
                System.currentTimeMillis() - this.cache.get(address).getCreated() < 60000L) {
            results = this.cache.get(address).getResults();
        } else {
            try {
                results = GeocodingApi.newRequest(this.geoApiContext).address(address).await();
            } catch (ApiException | InterruptedException | IOException e) {
                throw new GeocoderException("Exception occured when geocoding", e);
            }
        }

        if (this.cache.entrySet().size() > 50) {
            this.cache = new HashMap<>();
        }

        this.cache.put(address, new TimeCachedResult(results));
        return results;
    }

    protected class TimeCachedResult {
        private long created;
        private GeocodingResult[] results;

        protected TimeCachedResult(GeocodingResult[] results) {
            this.created = System.currentTimeMillis();
            this.results = results;
        }

        public GeocodingResult[] getResults() {
            return results;
        }

        public long getCreated() {
            return created;
        }
    }
}
