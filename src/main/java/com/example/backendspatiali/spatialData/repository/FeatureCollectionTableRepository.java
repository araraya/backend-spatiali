package com.example.backendspatiali.spatialData.repository;

import com.example.backendspatiali.spatialData.data.FeatureCollectionResponseProjection;
import com.example.backendspatiali.spatialData.data.FeatureCollectionTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FeatureCollectionTableRepository extends JpaRepository<FeatureCollectionTable, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO spatial_data_feature_collection(feature_collection_id, user_id, feature_id, properties, geometry)" +
            "VALUES(?1, ?2, ?3, ?4, ST_GeomFromGeoJSON(?5))", nativeQuery = true)
    void addFeatureCollection(Long featureCollectionId, UUID userId, Long featureId, String properties, String geometry);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT feature_collection_id as featureCollectionId, " +
            "user_id as userId, " +
            "feature_id as featureId, " +
            "properties as properties, " +
            "ST_AsGeoJSON(geometry) as geometry " +
            "FROM spatial_data_feature_collection WHERE user_id=?1", nativeQuery = true)
    List<FeatureCollectionResponseProjection> getFeatureCollectionByUser(UUID userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM spatial_data_feature_collection WHERE " +
            "feature_collection_id=?1", nativeQuery = true)
    void deleteFeatureCollection(Long featureCollectionId);
}
