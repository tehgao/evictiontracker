package org.dsacleveland.evictiontracker.service.mapper.legacy;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LegacyAddressMapperTest {

    private LegacyAddressMapper classUnderTest;

    @BeforeEach
    public void setUp() {
        this.classUnderTest = LegacyAddressMapper.INSTANCE;
    }

    @Test
    public void toDto() {
        // probably not worth testing
    }

    @Test
    public void toEntity() {
        Address address = Address.builder()
                                 .streetAddress("1234 ajfdkljsf jsfls")
                                 .streetAddress2("Apt 243")
                                 .city("Cleveland")
                                 .state("OH")
                                 .zip("12345")
                                 .build();

        AddressDto actual = this.classUnderTest.toEntity(address);

        assertEquals(address.getStreetAddress(), actual.getStreetAddress());
        assertEquals(address.getStreetAddress2(), actual.getStreetAddressSecondary());
        assertEquals(address.getCity(), actual.getCity());
        assertEquals(address.getState(), actual.getState());
        assertEquals(address.getZip(), actual.getZipCode());
    }
}