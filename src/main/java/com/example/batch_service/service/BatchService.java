package com.example.batch_service.service;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    
    public CohortDetail createCohort(CohortDetail request) {
        
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
    
 // *** NEW METHOD FOR BATCH ADD ***
    public List<CohortDetail> createCohortsBatch(List<CohortDetail> cohortDetailsList) {
        // You can add validation or default setting here if needed
        return cohortDetailRepository.saveAll(cohortDetailsList);
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
    
    
    public List<CohortDetail> getFilteredCohortsForDownload(
            String location,
            String facility, // Keep this parameter in the service method signature
            String building,
            Integer floorNumber, // Keep this parameter in the service method signature
            Integer roomNo,
            Integer minOccupancy,
            Integer maxOccupancy) {

        // Pass all parameters directly to the repository method.
        // The repository query will handle the 'NULL' checks for optional parameters.
        return cohortDetailRepository.findFilteredCohorts(
                location,
                facility, // Pass facility to the repository
                building,
                floorNumber, // Pass floorNumber to the repository
                roomNo,
                minOccupancy,
                maxOccupancy
        );
    }
    
    public Set<String> getAllLocations() {
        return cohortDetailRepository.findAll().stream()
                .map(CohortDetail::getLocation)
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new)); // Use TreeSet for natural sorting and uniqueness
    }
    
  

    public Set<String> getBuildingsByLocation(String location) {
        List<CohortDetail> filteredCohorts;
        if (location != null && !location.isEmpty()) {
            filteredCohorts = cohortDetailRepository.findByLocation(location);
        } else {
            filteredCohorts = cohortDetailRepository.findAll();
        }
        return filteredCohorts.stream()
                .map(CohortDetail::getBuilding)
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Integer> getRoomNosByLocationAndBuilding(String location, String building) {
        List<CohortDetail> filteredCohorts;
        if (location != null && !location.isEmpty() && building != null && !building.isEmpty()) {
            filteredCohorts = cohortDetailRepository.findByLocationAndBuilding(location, building);
        } else if (location != null && !location.isEmpty()) {
             // If building is not provided, filter only by location
            filteredCohorts = cohortDetailRepository.findByLocation(location);
        }
        else if (building != null && !building.isEmpty()) {
            // If location is not provided, filter only by building
            filteredCohorts = cohortDetailRepository.findByBuilding(building);
        }
        else {
            filteredCohorts = cohortDetailRepository.findAll();
        }

        return filteredCohorts.stream()
                .map(CohortDetail::getRoomNo)
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toCollection(TreeSet::new)); // TreeSet for sorted unique integers
    }
}