package com.polytech.alertcovidservicenotification.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactLocationRepository extends JpaRepository<ContactLocation,ContactLocationId> {
    @Query(value = "SELECT * FROM contact_location_history Where id_user = ?1 ORDER BY date Limit 1",nativeQuery = true)
    ContactLocation getLastLocationHistory(long id_user);
}
