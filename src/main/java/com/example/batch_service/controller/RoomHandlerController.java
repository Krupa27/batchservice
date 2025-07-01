package com.example.batch_service.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.batch_service.dto.RoomId;
import com.example.batch_service.service.RoomHandlerService;

@RestController

public class RoomHandlerController {
	@Autowired
	private RoomHandlerService rhs;
	
	@PostMapping("/roomOccupancy/{date}")
	public int getRoomOccupancy(@RequestBody RoomId roomId,@PathVariable String date) {
		LocalDate parsedDate = LocalDate.parse(date);
		return rhs.getRoomOccupancy(roomId,parsedDate);
	}
	
}
