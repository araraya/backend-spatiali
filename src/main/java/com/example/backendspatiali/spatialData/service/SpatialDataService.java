package com.example.backendspatiali.spatialData.service;


import com.example.backendspatiali.config.CryptoService;
import com.example.backendspatiali.config.SecretsProperties;
import com.example.backendspatiali.spatialData.data.ResponseProjection;
import com.example.backendspatiali.spatialData.data.SpatialDataRequest;
import com.example.backendspatiali.spatialData.data.SpatialDataUpdateRequest;
import com.example.backendspatiali.spatialData.data.UserSpatialResponse;
import com.example.backendspatiali.spatialData.repository.SpatialDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SpatialDataService {
    @Autowired
    SpatialDataRepository spatialDataRepository;
    @Autowired
    CryptoService cryptoService;
    @Autowired
    SecretsProperties secretsProperties;

    public SpatialDataService(SpatialDataRepository spatialDataRepository) {
        this.spatialDataRepository = spatialDataRepository;
    }

    public void addSpatialData(SpatialDataRequest spatialDataRequest) {
        var id = spatialDataRequest.getUserId();
        var geojson = spatialDataRequest.getGeojson();
        spatialDataRepository.addSpatialDataToGeom(String.valueOf(geojson.getProperties()),String.valueOf(geojson.getGeometry()), id);
    }

    public List<UserSpatialResponse> getUserSpatialData(UUID userId){
        List<UserSpatialResponse> UserSpatialResponses = new ArrayList<>();
        spatialDataRepository.getUserSpatialData(userId).forEach(s -> {
            var spatialData = UserSpatialResponse.builder()
                    .type("Feature")
                    .properties(cryptoService.encrypt(s.getProperties(), secretsProperties.getENCRYPTION()))

                    .geometry(cryptoService.encrypt(s.getGeometry(), secretsProperties.getENCRYPTION()))

                    .build();
            UserSpatialResponses.add(spatialData);
        });


        return UserSpatialResponses;

    }

    public void deleteSpatialData(Long id) {
        spatialDataRepository.deleteById(id);
    }

    public void updateSpatialData(SpatialDataUpdateRequest spatialDataUpdateRequest) {
        var id = spatialDataUpdateRequest.getId();
        var properties = spatialDataUpdateRequest.getFeature().getProperties();
        var geometry = spatialDataUpdateRequest.getFeature().getGeometry();
        spatialDataRepository.updateSpatialData(id, String.valueOf(properties), String.valueOf(geometry));
    }
}
