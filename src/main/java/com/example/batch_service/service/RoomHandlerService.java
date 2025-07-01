package com.example.batch_service.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.batch_service.dto.RoomId;
import com.example.batch_service.repository.CohortDetailRepository;
 
@Service
public class RoomHandlerService {
   
	
	private CohortDetailRepository cohortDetailRepository;
	public RoomHandlerService(CohortDetailRepository cohortDetailRepository) {
		this.cohortDetailRepository=cohortDetailRepository;
	}
	public int getRoomOccupancy(RoomId roomId, LocalDate date) {
		
		return cohortDetailRepository.findByRoomsBasedOnDate(roomId.getLocation(),roomId.getFacility(),roomId.getBuilding(),roomId.getFloorNumber(),roomId.getRoomNumber(),date);
	}
	
	
 
}