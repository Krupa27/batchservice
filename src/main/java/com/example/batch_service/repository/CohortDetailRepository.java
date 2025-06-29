package com.example.batch_service.repository;

import com.example.batch_service.model.CohortDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CohortDetailRepository extends JpaRepository<CohortDetail, String> {

    
    List<CohortDetail> findByLocationAndFacilityAndBuildingAndFloorNumberAndRoomNo(
            String location, String facility, String building, Integer floorNumber, Integer roomNo
    );
}