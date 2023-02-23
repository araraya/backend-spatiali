package com.example.backendspatiali.spatialData.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wololo.geojson.Geometry;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomGeojsonFeatureRes {
    private Long featureId;
    private String properties;
    private String geometry;
}
