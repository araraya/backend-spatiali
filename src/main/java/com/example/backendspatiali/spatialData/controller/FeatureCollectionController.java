package com.example.backendspatiali.spatialData.controller;


import com.example.backendspatiali.spatialData.data.CustomFeatureCollectionReq;
import com.example.backendspatiali.spatialData.data.CustomFeatureCollectionRes;
import com.example.backendspatiali.spatialData.data.FeatureCollectionResponseProjection;
import com.example.backendspatiali.spatialData.service.FeatureCollectionTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FeatureCollectionController {
    @Autowired
    FeatureCollectionTableService service;

    @PostMapping("/addFeatureCollection")
    public void addFeatureCollection(@RequestBody CustomFeatureCollectionReq featureCollection){
        service.addFeatureCollection(featureCollection);
    }

    @GetMapping("/getFeatureCollection/{userId}")
    public List<CustomFeatureCollectionRes> getUserFeatureCollection(@PathVariable("userId") UUID userId){
        return service.getUserFeatureCollection(userId);
    }

    @DeleteMapping("/deleteFeatureCollection/{FeatureCollectionId}")
    public ResponseEntity<String> deleteFeatureCollection(@PathVariable("FeatureCollectionId") Long featureCollectionId){
        service.deleteFeatureCollection(featureCollectionId);
        return ResponseEntity.ok("feature collection deleted");
    }

    @PutMapping("/updateFeatureCollection/{FeatureCollectionId}")
    public ResponseEntity<String> updateFeatureCollection(
            @PathVariable("FeatureCollectionId") Long featureCollectionId,
            @RequestBody CustomFeatureCollectionReq featureCollection){
        service.updateFeatureCollection(featureCollectionId,featureCollection);
                return ResponseEntity.ok("feature collection updated");
    }

}
