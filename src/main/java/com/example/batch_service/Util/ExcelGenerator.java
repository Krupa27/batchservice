package com.example.batch_service.Util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.batch_service.model.CohortDetail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId; // For converting LocalDate to java.util.Date
import java.util.Date; // For working with POI's date cells
import java.util.List;

public class ExcelGenerator {

    public static void generateCohortExcel(List<CohortDetail> cohorts, ByteArrayOutputStream outputStream) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Batches");

            // CellStyle for dates
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd")); // Common date format

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {
                "Batch Code",
                "In-Training Count",
                "Graduated Count",
                "Exit Count",
                "Training Start Date",
                "Training End Date",
                "Batch Owner",
                "Date Of Joining",
                "SL",
                "Practice",
                "Location",
                "Facility",
                "Building",
                "Floor Number",
                "Room No"
            };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Populate Data Rows
            int rowNum = 1;
            for (CohortDetail cohort : cohorts) {
                Row row = sheet.createRow(rowNum++);

                int colNum = 0;
                // Setters for String, Integer, and LocalDate fields
                row.createCell(colNum++).setCellValue(cohort.getCohortCode());
                safeSetCellValue(row.createCell(colNum++), cohort.getInTrainingCount());
                safeSetCellValue(row.createCell(colNum++), cohort.getGraduatedCount());
                safeSetCellValue(row.createCell(colNum++), cohort.getExitCount());

                // Handle LocalDate to Excel Date
                Cell trainingStartDateCell = row.createCell(colNum++);
                if (cohort.getTrainingStartDate() != null) {
                    trainingStartDateCell.setCellValue(convertLocalDateToDate(cohort.getTrainingStartDate()));
                    trainingStartDateCell.setCellStyle(dateCellStyle);
                }

                Cell trainingEndDateCell = row.createCell(colNum++);
                if (cohort.getTrainingEndDate() != null) {
                    trainingEndDateCell.setCellValue(convertLocalDateToDate(cohort.getTrainingEndDate()));
                    trainingEndDateCell.setCellStyle(dateCellStyle);
                }

                row.createCell(colNum++).setCellValue(cohort.getBatchOwner());

                Cell dateOfJoiningCell = row.createCell(colNum++);
                if (cohort.getDateOfJoining() != null) {
                    dateOfJoiningCell.setCellValue(convertLocalDateToDate(cohort.getDateOfJoining()));
                    dateOfJoiningCell.setCellStyle(dateCellStyle);
                }

                row.createCell(colNum++).setCellValue(cohort.getSl());
                row.createCell(colNum++).setCellValue(cohort.getPractice());
                row.createCell(colNum++).setCellValue(cohort.getLocation());
                row.createCell(colNum++).setCellValue(cohort.getFacility());
                row.createCell(colNum++).setCellValue(cohort.getBuilding());
                safeSetCellValue(row.createCell(colNum++), cohort.getFloorNumber());
                safeSetCellValue(row.createCell(colNum++), cohort.getRoomNo());
            }

            // Auto-size columns for better readability
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        }
    }

    // Helper method to safely set Integer values
    private static void safeSetCellValue(Cell cell, Integer value) {
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue(""); // Set empty string for null Integer values
        }
    }

    // Helper method to convert LocalDate to java.util.Date for Apache POI
    private static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
