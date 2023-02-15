package com.example.backendspatiali.spatialData.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wololo.geojson.Feature;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpatialDataUpdateRequest {
    private Long id;
    private Feature feature;
}
