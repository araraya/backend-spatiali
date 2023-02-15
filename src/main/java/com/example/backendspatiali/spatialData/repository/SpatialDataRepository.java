package com.example.backendspatiali.spatialData.repository;


import com.example.backendspatiali.spatialData.data.ResponseProjection;
import com.example.backendspatiali.spatialData.data.SpatialData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpatialDataRepository extends JpaRepository<SpatialData, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO spatial_data(type, properties, geometry, user_id)" +
            "VALUES('Feature',?1, ST_GeomFromGeoJSON(?2), ?3)", nativeQuery = true)
    void addSpatialDataToGeom(String properties, String geometry, UUID userId);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT type as type, properties as properties, ST_AsGeoJSON(geometry) as geometry FROM spatial_data WHERE user_id=?1", nativeQuery = true)
    List<ResponseProjection> getUserSpatialData(UUID userId);

}
