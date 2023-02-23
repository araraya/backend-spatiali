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
public class CustomGeojsonFeatureReq {
    private Long featureId;
    private Map<String, Object> properties;
    private Geometry geometry;
}
