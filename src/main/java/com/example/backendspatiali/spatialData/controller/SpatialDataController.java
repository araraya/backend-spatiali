package com.example.backendspatiali.spatialData.controller;


import com.example.backendspatiali.spatialData.data.GeojsonResponse;
import com.example.backendspatiali.spatialData.data.Geometry;
import com.example.backendspatiali.spatialData.data.ResponseProjection;
import com.example.backendspatiali.spatialData.data.SpatialDataRequest;
import com.example.backendspatiali.spatialData.repository.SpatialDataRepository;
import com.example.backendspatiali.spatialData.service.SpatialDataService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wololo.geojson.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class SpatialDataController {
    @Autowired
    SpatialDataService spatialDataService;
    @Autowired
    private SpatialDataRepository spatialDataRepository;
    private JavaType Geometry;

    @PostMapping("/addSpatialData")
    public void addSpatialData(@RequestBody SpatialDataRequest spatialDataRequest){
        spatialDataService.addSpatialData(spatialDataRequest);
    }

    @GetMapping("/getUserSpatialData/{userId}")
    public List<ResponseProjection> getUserSpatialData(@PathVariable("userId") UUID userId){
        return spatialDataRepository.getUserSpatialData(userId);
    }

}
