package com.example.backendspatiali.spatialData.data;


import com.example.backendspatiali.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Geometry;
import org.wololo.geojson.Feature;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spatial_data")
public class SpatialData {
    @Id
    @GeneratedValue
    private Long id;


    private String properties;
    private Geometry geometry;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "spatial_datas")
    @JsonIgnore
    private Set<User> userId = new HashSet<>();

}
