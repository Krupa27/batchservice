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

 // --- UPDATED CUSTOM QUERY FOR FILTERED DOWNLOAD ---
    @Query("SELECT c FROM CohortDetail c WHERE " +
           "(:location IS NULL OR c.location = :location) AND " +
           "(:facility IS NULL OR c.facility = :facility) AND " + // Keep facility for consistency, although frontend doesn't use it now
           "(:building IS NULL OR c.building = :building) AND " +
           "(:floorNumber IS NULL OR c.floorNumber = :floorNumber) AND " + // Keep floorNumber for consistency
           "(:roomNo IS NULL OR c.roomNo = :roomNo) AND " +
           "(:minOccupancy IS NULL OR c.inTrainingCount >= :minOccupancy) AND " +
           "(:maxOccupancy IS NULL OR c.inTrainingCount <= :maxOccupancy)")
    List<CohortDetail> findFilteredCohorts(
            @Param("location") String location,
            @Param("facility") String facility, // Allow null from service
            @Param("building") String building,
            @Param("floorNumber") Integer floorNumber, // Allow null from service
            @Param("roomNo") Integer roomNo,
            @Param("minOccupancy") Integer minOccupancy,
            @Param("maxOccupancy") Integer maxOccupancy
    );
    
    List<CohortDetail> findByLocation(String location);
    List<CohortDetail> findByBuilding(String building); // Added for completeness, though cascading usually handles this
    List<CohortDetail> findByLocationAndBuilding(String location, String building);


    
    
    
}