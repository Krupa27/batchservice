package com.example.batch_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batch_service.dto.RoomId;

@RestController

public class RoomHandlerController {
	
	@GetMapping("/roomOccupancy")
	public int getRoomOccupancy(RoomId roomId, String date) {
		return roomId.getRoomNumber();
	}
	
}
