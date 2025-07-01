package com.example.batch_service.repository;

import com.example.batch_service.model.CohortDetail;

import feign.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CohortDetailRepository extends JpaRepository<CohortDetail, String> {

    
    List<CohortDetail> findByLocationAndFacilityAndBuildingAndFloorNumberAndRoomNo(
            String location, String facility, String building, Integer floorNumber, Integer roomNo
    );
    
    
    @Query("""
    	    SELECT COALESCE(SUM(c.inTrainingCount), 0)
    	    FROM CohortDetail c
    	    WHERE c.location = :location
    	      AND c.facility = :facility
    	      AND c.building = :building
    	      AND c.floorNumber = :floorNumber
    	      AND c.roomNo = :roomNo
    	      AND c.trainingStartDate <= :date
    	""")
    	Integer findByRoomsBasedOnDate(
    	    @Param("location") String location,
    	    @Param("facility") String facility,
    	    @Param("building") String building,
    	    @Param("floorNumber") Integer floorNumber,
    	    @Param("roomNo") Integer roomNo,
    	    @Param("date") LocalDate date
    	);

   


    
    
    
}