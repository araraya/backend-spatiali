package com.example.backendspatiali.spatialData.repository;

import com.example.backendspatiali.spatialData.data.FeatureCollectionTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FeatureCollectionTableRepository extends JpaRepository<FeatureCollectionTable, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO spatial_data_feature_collection(feature_collection_id, feature_id, properties, geometry, user_id)" +
            "VALUES(?1, ?2, ?3, ST_GeomFromGeoJSON(?4), ?5)", nativeQuery = true)
    void addFeatureCollection(Long featureCollectionId, Long featureId, String properties, String geometry, UUID userId);

}
