package com.example.backendspatiali.spatialData.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry {
    @JsonProperty("type")
    public String type;
    @JsonProperty("coordinates")
    public ArrayList coordinates;
}
