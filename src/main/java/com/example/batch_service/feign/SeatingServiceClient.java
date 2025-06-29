package com.example.batch_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "seating-service", url = "${seating.service.url}") 
public interface SeatingServiceClient {

    
    @GetMapping("/api/rooms/checkExistence")
    Boolean checkRoomExistence(
            @RequestParam("location") String location,
            @RequestParam("facility") String facility,
            @RequestParam("building") String building,
            @RequestParam("floorNumber") Integer floorNumber,
            @RequestParam("roomNo") Integer roomNo
    );

}