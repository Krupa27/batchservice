package com.example.batch_service.controller;

import com.example.batch_service.Util.BatchCsvHelper;
import com.example.batch_service.Util.ExcelGenerator;
import com.example.batch_service.dto.CohortCreationRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.MediaType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import com.example.batch_service.model.CohortDetail;
import com.example.batch_service.service.BatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/batches")
@CrossOrigin("*")

public class BatchController {

    private final BatchService batchService;

    @Autowired
    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/add_batch")
    public ResponseEntity<CohortDetail> createBatch(@Valid @RequestBody CohortDetail request) {
        try {
            CohortDetail newCohort = batchService.createCohort(request);
            System.out.println("CreateBatch method");
            return new ResponseEntity<>(newCohort, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            // Catch any unexpected errors and return 500
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
 // *** NEW ENDPOINT FOR BATCH ADD ***
    @PostMapping("/upload-batch") // Changed endpoint name for clarity
    public ResponseEntity<String> uploadCohortDetailsBatch(@RequestBody List<CohortDetail> cohortDetailsList) {
        try {
            if (cohortDetailsList == null || cohortDetailsList.isEmpty()) {
                return new ResponseEntity<>("No cohort details provided for batch upload.", HttpStatus.BAD_REQUEST);
            }
            batchService.createCohortsBatch(cohortDetailsList); // Use the new service method
            return new ResponseEntity<>("Cohort details uploaded in batch successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload cohort details in batch: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
    
    
    @GetMapping("/download_batches")
    public ResponseEntity<byte[]> downloadBatches(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String building,
            @RequestParam(required = false) Integer roomNo,
            @RequestParam(required = false) Integer minOccupancy,
            @RequestParam(required = false) Integer maxOccupancy) {

        List<CohortDetail> cohorts = batchService.getFilteredCohortsForDownload(
                location,
                null,
                building,
                null,
                roomNo,
                minOccupancy,
                maxOccupancy
        );

        if (cohorts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Use the new ExcelGenerator to write data to the output stream
            ExcelGenerator.generateCohortExcel(cohorts, bos);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=batches.xlsx");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(bos.toByteArray());

        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    
    // Endpoint to get unique locations (no change needed unless you want to filter them later)
    @GetMapping("/locations")
    public ResponseEntity<Set<String>> getUniqueLocations() {
        Set<String> locations = batchService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    // Endpoint to get unique buildings, optionally filtered by location
    @GetMapping("/buildings")
    public ResponseEntity<Set<String>> getUniqueBuildings(
            @RequestParam(required = false) String location) {
        Set<String> buildings = batchService.getBuildingsByLocation(location);
        return ResponseEntity.ok(buildings);
    }

    // Endpoint to get unique room numbers, optionally filtered by location and building
    @GetMapping("/roomNos")
    public ResponseEntity<Set<Integer>> getUniqueRoomNumbers(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String building) {
        Set<Integer> roomNos = batchService.getRoomNosByLocationAndBuilding(location, building);
        return ResponseEntity.ok(roomNos);
    }
    
    
    
    
    
}