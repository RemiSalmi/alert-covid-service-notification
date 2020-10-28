package com.polytech.alertcovidservicenotification.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="contact_location_history")
@IdClass(ContactLocationId.class)
@Access(AccessType.FIELD)
public class ContactLocation {
    @Id
    private long id_user;
    @Id
    private Timestamp date;
    @Id
    private Double longitude;
    @Id
    private Double latitude;

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
