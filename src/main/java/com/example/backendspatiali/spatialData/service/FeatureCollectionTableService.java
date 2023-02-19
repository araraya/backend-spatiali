package com.example.backendspatiali.spatialData.service;

import com.example.backendspatiali.spatialData.data.CustomFeatureCollection;
import com.example.backendspatiali.spatialData.data.CustomGeojsonFeature;
import com.example.backendspatiali.spatialData.repository.FeatureCollectionTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureCollectionTableService {

    @Autowired
    FeatureCollectionTableRepository repository;

    public void addFeatureCollection(CustomFeatureCollection featureCollection){
        List<CustomGeojsonFeature> features = featureCollection.getFeatures();
        features.forEach(f -> {
            repository.addFeatureCollection(featureCollection.getFeatureCollectionId(), f.getFeatureId(), String.valueOf(f.getProperties()),String.valueOf(f.getGeometry()), featureCollection.getUserId());
        });

    }
}
