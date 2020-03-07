package org.dsacleveland.evictiontracker.model.evictiondata.type;

public interface Party<D extends Address, T extends Attorney> {
    String getName();

    D getAddress();

    T getAttorney();

    void setName(String name);

    void setAddress(D address);

    void setAttorney(T attorney);
}
