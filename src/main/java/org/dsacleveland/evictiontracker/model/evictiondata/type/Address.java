package org.dsacleveland.evictiontracker.model.evictiondata.type;

import java.util.Optional;

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

    default String toPrintableString() {
        StringBuilder output = new StringBuilder(this.getStreetAddress());
        output.append("\n");

        Optional.ofNullable(this.getStreetAddressSecondary())
                .filter(sa2 -> !sa2.equals(""))
                .ifPresent(sa2 -> output.append(sa2 + "\n"));

        output.append(this.getCity().concat(", "));
        output.append(this.getState().concat(" "));
        output.append(this.getZipCode());

        return output.toString();
    }
}
