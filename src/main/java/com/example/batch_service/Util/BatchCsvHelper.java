package com.example.batch_service.Util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.example.batch_service.model.CohortDetail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class BatchCsvHelper {
	 public static ByteArrayInputStream cohortsToCsv(List<CohortDetail> cohorts) {
	        // Updated CSVFormat to use .DEFAULT which handles quoting and delimiters
	        // Changed to use org.apache.commons.csv.QuoteMode.MINIMAL for cleaner CSV
	        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(org.apache.commons.csv.QuoteMode.MINIMAL);

	        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {

	            // Header row - ensure these match the fields you want in the CSV
	            csvPrinter.printRecord(
	                "Cohort Code", "In Training Count", "Graduated Count", "Exit Count",
	                "Training Start Date", "Training End Date", "Batch Owner", "Date Of Joining",
	                "SL", "Practice", "Location", "Facility", "Building", "Floor Number", "Room No"
	            );

	            // Data rows
	            for (CohortDetail cohort : cohorts) {
	                // Ensure all fields are handled, even if null, to avoid NullPointerExceptions
	                // Use empty string for nulls or convert numbers to string
	                csvPrinter.printRecord(
	                    cohort.getCohortCode(),
	                    String.valueOf(cohort.getInTrainingCount() != null ? cohort.getInTrainingCount() : ""),
	                    String.valueOf(cohort.getGraduatedCount() != null ? cohort.getGraduatedCount() : ""),
	                    String.valueOf(cohort.getExitCount() != null ? cohort.getExitCount() : ""),
	                    cohort.getTrainingStartDate() != null ? cohort.getTrainingStartDate().toString() : "",
	                    cohort.getTrainingEndDate() != null ? cohort.getTrainingEndDate().toString() : "",
	                    cohort.getBatchOwner(),
	                    cohort.getDateOfJoining() != null ? cohort.getDateOfJoining().toString() : "",
	                    cohort.getSl(),
	                    cohort.getPractice(),
	                    cohort.getLocation(),
	                    cohort.getFacility(),
	                    cohort.getBuilding(),
	                    String.valueOf(cohort.getFloorNumber() != null ? cohort.getFloorNumber() : ""),
	                    String.valueOf(cohort.getRoomNo() != null ? cohort.getRoomNo() : "")
	                );
	            }

	            csvPrinter.flush();
	            return new ByteArrayInputStream(out.toByteArray());
	        } catch (IOException e) {
	            // It's good practice to log the error for debugging purposes
	            System.err.println("Error generating CSV: " + e.getMessage());
	            throw new RuntimeException("Failed to generate CSV file: " + e.getMessage(), e);
	        }
	    }
}
