package com.example.backendspatiali.spatialData.data;

import org.wololo.geojson.Geometry;

public interface FeatureCollectionResponseProjection {
    Long getFeatureCollectionId();
    String getUserId();
    String getFeatureId();
    String getProperties();
    String getGeometry();

}
