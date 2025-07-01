package com.example.batch_service.controller;

import com.example.batch_service.dto.CohortCreationRequest;
import com.example.batch_service.model.CohortDetail;
import com.example.batch_service.service.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/add_batch")
    public ResponseEntity<CohortDetail> createBatch(@Valid @RequestBody CohortCreationRequest request) {
        try {
            CohortDetail newCohort = batchService.createCohort(request);
            return new ResponseEntity<>(newCohort, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            // Catch any unexpected errors and return 500
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search_room")
    public ResponseEntity<List<CohortDetail>> getBatchesByRoom(
            @RequestParam String location,
            @RequestParam String facility,
            @RequestParam String building,
            @RequestParam Integer floorNumber,
            @RequestParam Integer roomNo) {

        List<CohortDetail> cohorts = batchService.getCohortsByRoomDetails(
                location, facility, building, floorNumber, roomNo
        );

        if (cohorts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(cohorts, HttpStatus.OK); 
    }
    
    @GetMapping("/search_by_cohort_code")
    public ResponseEntity<CohortDetail> getCohortByCode(@RequestParam String cohortCode) {
        Optional<CohortDetail> cohort = batchService.getCohortByCode(cohortCode);

        if (cohort.isPresent()) {
            return new ResponseEntity<>(cohort.get(), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
    
    
    
    
    
}