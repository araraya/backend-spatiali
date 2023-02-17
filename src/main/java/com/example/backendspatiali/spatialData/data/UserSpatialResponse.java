package com.example.backendspatiali.spatialData.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSpatialResponse {
    private String type;
    private String properties;
    private String geometry;
}
