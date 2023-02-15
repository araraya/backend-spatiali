package com.example.backendspatiali.spatialData.data;


import com.example.backendspatiali.user.data.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spatial_data")
public class SpatialData {
    @Id
    @GeneratedValue
    private Long id;
    private String type = "Feature";
    private String properties;
    @Column(name="geometry", columnDefinition = "Geometry")
    private Geometry geometry;
    private UUID userId;

}
