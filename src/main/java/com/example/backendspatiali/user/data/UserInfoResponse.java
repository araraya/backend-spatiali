package com.example.backendspatiali.user.data;


import com.example.backendspatiali.spatialData.data.SpatialData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.spatial.Spatial;
import org.wololo.geojson.Feature;

import java.util.ArrayList;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private String username;
    private Set<SpatialData> spatial_data;
}
