package com.example.backendspatiali.spatialData.service;

import com.example.backendspatiali.spatialData.data.*;
import com.example.backendspatiali.spatialData.repository.FeatureCollectionTableRepository;
import com.example.backendspatiali.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeatureCollectionTableService {

    @Autowired
    FeatureCollectionTableRepository repository;
    @Autowired
    private UserRepository userRepository;

    public void addFeatureCollection(CustomFeatureCollectionReq featureCollection){
        List<CustomGeojsonFeatureReq> features = featureCollection.getFeatures();
        features.forEach(f -> {
            repository.addFeatureCollection(
                    featureCollection.getFeatureCollectionId(),
                    featureCollection.getUserId(),
                    f.getFeatureId(),
                    String.valueOf(f.getProperties()),
                    String.valueOf(f.getGeometry()));
        });

    }

    public List<CustomFeatureCollectionRes> getUserFeatureCollection(UUID userId){
        var rawFromDB = repository.getFeatureCollectionByUser(userId);
        var countOfFeatureCollection = new ArrayList<>();

        rawFromDB.forEach(raw -> {
            countOfFeatureCollection.add(raw.getFeatureCollectionId());
        });
        var featureCollectionIdWithoutDuplicate = new ArrayList<>(new HashSet<>(countOfFeatureCollection));
        System.out.println(featureCollectionIdWithoutDuplicate.toString());
        List<CustomFeatureCollectionRes> processedFeatureCollectionResponse = new ArrayList<>();

        featureCollectionIdWithoutDuplicate.forEach(f -> {
            List<FeatureCollectionResponseProjection> filteredFeatureProjection = rawFromDB
                    .stream().filter(r -> r.getFeatureCollectionId() == f).collect(Collectors.toList());

            System.out.println(rawFromDB.get(0).getFeatureCollectionId());
            System.out.println(f);
            System.out.println(filteredFeatureProjection);
            List<CustomGeojsonFeatureRes> filteredFeatureToResponse = new ArrayList<>();

            filteredFeatureProjection.forEach(filtered -> {
                CustomGeojsonFeatureRes convertingFromProjection = CustomGeojsonFeatureRes.builder()
                                        .featureId(Long.valueOf(filtered.getFeatureId()))
                                        .properties(filtered.getProperties())
                        .geometry(filtered.getGeometry())
                        .build();
                filteredFeatureToResponse.add(convertingFromProjection);
            });


            CustomFeatureCollectionRes featureCollectionRes = CustomFeatureCollectionRes.builder()
                    .featureCollectionId(Long.valueOf(f.toString()))
                    .type("FeatureCollection")
                    .userId(userId)
                    .features(filteredFeatureToResponse)
                    .build();

            processedFeatureCollectionResponse.add(featureCollectionRes);
        });

        return processedFeatureCollectionResponse;
    }
}