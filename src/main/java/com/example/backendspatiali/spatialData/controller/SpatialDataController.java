package com.example.backendspatiali.spatialData.controller;


import com.example.backendspatiali.spatialData.data.*;
import com.example.backendspatiali.spatialData.repository.SpatialDataRepository;
import com.example.backendspatiali.spatialData.service.SpatialDataService;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SpatialDataController {
    @Autowired
    SpatialDataService spatialDataService;

    @PostMapping("/addSpatialData")
    public void addSpatialData(@RequestBody SpatialDataRequest spatialDataRequest){
        spatialDataService.addSpatialData(spatialDataRequest);
    }

    @GetMapping("/getUserSpatialData/{userId}")
    public List<UserSpatialResponse> getUserSpatialData(@PathVariable("userId") UUID userId){
        return spatialDataService.getUserSpatialData(userId);
    }

    @DeleteMapping("/deleteSpatialData/{id}")
    public ResponseEntity<String> deleteSpatialData(@PathVariable("id") Long id){
        spatialDataService.deleteSpatialData(id);
      return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/updateSpatialData")
    public ResponseEntity<String> updateSpatialData(@RequestBody SpatialDataUpdateRequest spatialDataUpdateRequest){
        spatialDataService.updateSpatialData(spatialDataUpdateRequest);
        return ResponseEntity.ok("Updated");
    }
}
