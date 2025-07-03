package com.example.batch_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects; 

@Entity
@Table(name = "cohort_details") 
public class CohortDetail {

    @Id // Marks cohortCode as the primary key
    private String cohortCode;

    private Integer inTrainingCount;
    private Integer graduatedCount;
    private Integer exitCount;

    private LocalDate trainingStartDate;
    private LocalDate trainingEndDate;
    private String batchOwner;
    private LocalDate dateOfJoining;
    private String sl;
    private String practice;

    // Room details 
    private String location;
    private String facility;
    private String building;
    private Integer floorNumber;
      private Integer roomNo;

  
    public CohortDetail() {
    }

    
    public CohortDetail(String cohortCode, Integer inTrainingCount, Integer graduatedCount, Integer exitCount,
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

    // --- Getters ---
    // Manual Getters for all fields
    public String getCohortCode() { return cohortCode; }
    public Integer getInTrainingCount() { return inTrainingCount; }
    public Integer getGraduatedCount() { return graduatedCount; }
    public Integer getExitCount() { return exitCount; }
    public LocalDate getTrainingStartDate() { return trainingStartDate; }
    public LocalDate getTrainingEndDate() { return trainingEndDate; }
    public String getBatchOwner() { return batchOwner; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public String getSl() { return sl; }
    public String getPractice() { return practice; }
    public String getLocation() { return location; }
    public String getFacility() { return facility; }
    public String getBuilding() { return building; }
    public Integer getFloorNumber() { return floorNumber; }
    public Integer getRoomNo() { return roomNo; }

    // --- Setters ---
    // Manual Setters for all fields
    public void setCohortCode(String cohortCode) { this.cohortCode = cohortCode; }
    public void setInTrainingCount(Integer inTrainingCount) { this.inTrainingCount = inTrainingCount; }
    public void setGraduatedCount(Integer graduatedCount) { this.graduatedCount = graduatedCount; }
    public void setExitCount(Integer exitCount) { this.exitCount = exitCount; }
    public void setTrainingStartDate(LocalDate trainingStartDate) { this.trainingStartDate = trainingStartDate; }
    public void setTrainingEndDate(LocalDate trainingEndDate) { this.trainingEndDate = trainingEndDate; }
    public void setBatchOwner(String batchOwner) { this.batchOwner = batchOwner; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }
    public void setSl(String sl) { this.sl = sl; }
    public void setPractice(String practice) { this.practice = practice; }
    public void setLocation(String location) { this.location = location; }
    public void setFacility(String facility) { this.facility = facility; }
    public void setBuilding(String building) { this.building = building; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }
    public void setRoomNo(Integer roomNo) { this.roomNo = roomNo; }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CohortDetail that = (CohortDetail) o;
        return Objects.equals(cohortCode, that.cohortCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cohortCode);
    }

    @Override
    public String toString() {
        return "CohortDetail{" +
               "cohortCode='" + cohortCode + '\'' +
               ", inTrainingCount=" + inTrainingCount +
               ", location='" + location + '\'' +
               ", roomNo=" + roomNo +
               '}';
    }
}