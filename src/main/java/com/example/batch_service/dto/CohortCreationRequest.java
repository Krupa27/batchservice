package com.example.batch_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class CohortCreationRequest {

    @NotBlank(message = "Cohort code is mandatory")
    @Size(max = 50, message = "Cohort code cannot exceed 50 characters")
    private String cohortCode;

    @Min(value = 0, message = "In training count cannot be negative")
    private Integer inTrainingCount;

    @Min(value = 0, message = "Graduated count cannot be negative")
    private Integer graduatedCount;

    @Min(value = 0, message = "Exit count cannot be negative")
    private Integer exitCount;

    @NotNull(message = "Training start date is mandatory")
    private LocalDate trainingStartDate;

    @NotNull(message = "Training end date is mandatory")
    private LocalDate trainingEndDate;

    @NotBlank(message = "Batch owner is mandatory")
    private String batchOwner;

    @NotNull(message = "Date of joining is mandatory")
    private LocalDate dateOfJoining;

    @NotBlank(message = "SL is mandatory")
    private String sl;

    @NotBlank(message = "Practice is mandatory")
    private String practice;

    @NotBlank(message = "Location is mandatory")
    private String location;

    @NotBlank(message = "Facility is mandatory")
    private String facility;

    @NotBlank(message = "Building is mandatory")
    private String building;

    @NotNull(message = "Floor number is mandatory")
    @Min(value = 0, message = "Floor number cannot be negative")
    private Integer floorNumber;

    @NotNull(message = "Room number is mandatory")
    @Min(value = 0, message = "Room number cannot be negative")
    private Integer roomNo;

    // --- Constructors ---
    public CohortCreationRequest() {
    }

    public CohortCreationRequest(String cohortCode, Integer inTrainingCount, Integer graduatedCount, Integer exitCount,
                                 LocalDate trainingStartDate, LocalDate trainingEndDate, String batchOwner,
                                 LocalDate dateOfJoining, String sl, String practice,
                                 String location, String facility, String building, Integer floorNumber, Integer roomNo) {
        this.cohortCode = cohortCode;
        this.inTrainingCount = inTrainingCount;
        this.graduatedCount = graduatedCount;
        this.exitCount = exitCount;
        this.trainingStartDate = trainingStartDate;
        this.trainingEndDate = trainingEndDate;
        this.batchOwner = batchOwner;
        this.dateOfJoining = dateOfJoining;
        this.sl = sl;
        this.practice = practice;
        this.location = location;
        this.facility = facility;
        this.building = building;
        this.floorNumber = floorNumber;
        this.roomNo = roomNo;
    }

	public String getCohortCode() {
		return cohortCode;
	}

	public void setCohortCode(String cohortCode) {
		this.cohortCode = cohortCode;
	}

	public Integer getInTrainingCount() {
		return inTrainingCount;
	}

	public void setInTrainingCount(Integer inTrainingCount) {
		this.inTrainingCount = inTrainingCount;
	}

	public Integer getGraduatedCount() {
		return graduatedCount;
	}

	public void setGraduatedCount(Integer graduatedCount) {
		this.graduatedCount = graduatedCount;
	}

	public Integer getExitCount() {
		return exitCount;
	}

	public void setExitCount(Integer exitCount) {
		this.exitCount = exitCount;
	}

	public LocalDate getTrainingStartDate() {
		return trainingStartDate;
	}

	public void setTrainingStartDate(LocalDate trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}

	public LocalDate getTrainingEndDate() {
		return trainingEndDate;
	}

	public void setTrainingEndDate(LocalDate trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}

	public String getBatchOwner() {
		return batchOwner;
	}

	public void setBatchOwner(String batchOwner) {
		this.batchOwner = batchOwner;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

    
}