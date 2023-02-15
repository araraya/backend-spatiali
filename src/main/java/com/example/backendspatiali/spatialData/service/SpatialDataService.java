package com.example.backendspatiali.spatialData.service;


import com.example.backendspatiali.spatialData.data.ResponseProjection;
import com.example.backendspatiali.spatialData.data.SpatialDataRequest;
import com.example.backendspatiali.spatialData.repository.SpatialDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpatialDataService {
    private final SpatialDataRepository spatialDataRepository;

    public SpatialDataService(SpatialDataRepository spatialDataRepository) {
        this.spatialDataRepository = spatialDataRepository;
    }


    public void addSpatialData(SpatialDataRequest spatialDataRequest) {
        var id = spatialDataRequest.getUserId();
        var geojson = spatialDataRequest.getGeojson();
        spatialDataRepository.addSpatialDataToGeom(String.valueOf(geojson.getProperties()),String.valueOf(geojson.getGeometry()), id);
    }

    public List<ResponseProjection> getUserSpatialData(UUID userId){
        return spatialDataRepository.getUserSpatialData(userId);

    }
}
