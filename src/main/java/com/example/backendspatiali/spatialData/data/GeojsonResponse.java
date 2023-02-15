package com.example.backendspatiali.spatialData.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeojsonResponse {

    private String type;
    private Object properties;
    private Object geometry;
}
