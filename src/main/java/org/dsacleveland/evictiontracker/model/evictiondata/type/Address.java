package org.dsacleveland.evictiontracker.model.evictiondata.type;

public interface Address {
    String getStreetAddress();

    String getStreetAddressSecondary();

    String getCity();

    String getState();

    String getZipCode();

    void setStreetAddress(String streetAddress);

    void setStreetAddressSecondary(String streetAddressSecondary);

    void setCity(String city);

    void setState(String state);

    void setZipCode(String zipCode);
}
