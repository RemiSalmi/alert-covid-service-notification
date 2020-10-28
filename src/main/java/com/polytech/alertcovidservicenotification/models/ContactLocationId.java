package com.polytech.alertcovidservicenotification.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ContactLocationId implements Serializable {
    private long id_user;
    private Timestamp date;
    private Double longitude;
    private Double latitude;

    public ContactLocationId(){}

    public ContactLocationId(long id_user, Timestamp date, Double longitude, Double latitude) {
        this.id_user = id_user;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user,date,longitude,latitude);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass())return false;
        ContactLocationId contactlocationId = (ContactLocationId) obj;
        return id_user == contactlocationId.id_user && date.equals(contactlocationId.date) && longitude.equals(contactlocationId.longitude) && latitude.equals(contactlocationId.latitude);
    }
}
