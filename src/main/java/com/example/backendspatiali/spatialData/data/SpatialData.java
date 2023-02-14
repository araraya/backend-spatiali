package com.example.backendspatiali.spatialData.data;


import com.example.backendspatiali.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Geometry;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
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
            mappedBy = "spatial_data")
    @JsonIgnore
    private Set<User> userId = new HashSet<>();

}
