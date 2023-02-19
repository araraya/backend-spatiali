package com.example.backendspatiali.spatialData.controller;


import com.example.backendspatiali.spatialData.data.CustomFeatureCollection;
import com.example.backendspatiali.spatialData.service.FeatureCollectionTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class FeatureCollectionController {
    @Autowired
    FeatureCollectionTableService service;

    @PostMapping("/addFeatureCollection")
    public void addFeatureCollection(@RequestBody CustomFeatureCollection featureCollection){
        service.addFeatureCollection(featureCollection);
    }

    @GetMapping("/getFeatureCollection/{userId}")
    public void getUserFeatureCollection(@PathVariable("userId") UUID userId){

    }
}
