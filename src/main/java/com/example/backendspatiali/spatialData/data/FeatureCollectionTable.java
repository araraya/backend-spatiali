package com.example.backendspatiali.spatialData.data;


import com.vividsolutions.jts.geom.Geometry;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@Entity
@Table(name = "spatial_data_feature_collection")
@AllArgsConstructor
@NoArgsConstructor
public class FeatureCollectionTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long featureCollectionId;
    private Long featureId;
    private UUID userId;
    private String properties;
    @Column(name="geometry", columnDefinition = "Geometry")
    private Geometry geometry;
}
