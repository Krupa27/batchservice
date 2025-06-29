package com.example.batch_service.service;

import java.util.Optional;
import com.example.batch_service.model.CohortDetail;
import com.example.batch_service.repository.CohortDetailRepository;
import com.example.batch_service.dto.CohortCreationRequest;
import com.example.batch_service.feign.SeatingServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    private final CohortDetailRepository cohortDetailRepository;
    private final SeatingServiceClient feign;
    @Autowired
    public BatchService(CohortDetailRepository cohortDetailRepository,SeatingServiceClient feign) {
        this.cohortDetailRepository = cohortDetailRepository;
        this.feign=feign;
    }

    
    public CohortDetail createCohort(CohortCreationRequest request) {
        
        CohortDetail cohortDetail = new CohortDetail();
        cohortDetail.setCohortCode(request.getCohortCode());
        cohortDetail.setInTrainingCount(request.getInTrainingCount());
        cohortDetail.setGraduatedCount(request.getGraduatedCount());
        cohortDetail.setExitCount(request.getExitCount());
        cohortDetail.setTrainingStartDate(request.getTrainingStartDate());
        cohortDetail.setTrainingEndDate(request.getTrainingEndDate());
        cohortDetail.setBatchOwner(request.getBatchOwner());
        cohortDetail.setDateOfJoining(request.getDateOfJoining());
        cohortDetail.setSl(request.getSl());
        cohortDetail.setPractice(request.getPractice());
        cohortDetail.setLocation(request.getLocation());
        cohortDetail.setFacility(request.getFacility());
        cohortDetail.setBuilding(request.getBuilding());
        cohortDetail.setFloorNumber(request.getFloorNumber());
        cohortDetail.setRoomNo(request.getRoomNo());

     
        return cohortDetailRepository.save(cohortDetail);
    }

    
    public List<CohortDetail> getCohortsByRoomDetails(String location, String facility,
                                                     String building, Integer floorNumber, Integer roomNo) {
        return cohortDetailRepository.findByLocationAndFacilityAndBuildingAndFloorNumberAndRoomNo(
            location, facility, building, floorNumber, roomNo
        );
    }
    
    
    public Optional<CohortDetail> getCohortByCode(String cohortCode) {
        return cohortDetailRepository.findById(cohortCode); 
    }
}