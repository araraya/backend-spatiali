package com.example.backendspatiali.spatialData.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomFeatureCollection {
    private String type;
    private Long featureCollectionId;
    private UUID userId;
    private List<CustomGeojsonFeature> features;
}
