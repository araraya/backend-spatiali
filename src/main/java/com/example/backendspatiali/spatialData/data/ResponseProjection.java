package com.example.backendspatiali.spatialData.data;

import org.json.JSONObject;

public interface ResponseProjection {
    String getType();
    String getProperties();
    String getGeometry();
}
