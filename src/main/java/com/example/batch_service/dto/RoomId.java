package com.example.batch_service.dto;

import jakarta.persistence.Embeddable;


import java.io.Serializable;
import jakarta.persistence.Column;

public class RoomId implements Serializable {
	
	@Column(length = 50) 
    private String location;

    @Column(length = 50) 
    private String facility;

    @Column(length = 50) 
    private String building;

    private Integer floorNumber; 

    @Column(length = 20) 
    private String wing;
    
    private Integer roomNumber;
    public RoomId() {
    	
    }
    public RoomId(String location, String facility, String building, Integer floorNumber, String wing, Integer roomNumber) {
		super();
		this.location = location;
		this.facility = facility;
		this.building = building;
		this.floorNumber = floorNumber;
		this.wing = wing;
		this.roomNumber = roomNumber;
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
	public String getWing() {
		return wing;
	}
	public void setWing(String wing) {
		this.wing = wing;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
}