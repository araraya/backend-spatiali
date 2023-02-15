package com.example.backendspatiali.spatialData.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wololo.geojson.Feature;

import java.awt.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpatialDataRequest {
    private UUID userId;
    private Feature geojson;
}
