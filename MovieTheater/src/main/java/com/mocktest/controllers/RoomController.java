package com.mocktest.controllers;

import com.mocktest.bean.request.RoomRequest;
import com.mocktest.bean.response.RoomResponse;
import com.mocktest.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;
    @PostMapping("/room")
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest request){
        return new ResponseEntity<>(roomService.create(request), HttpStatus.OK);
    }
    @GetMapping("/room")
    public ResponseEntity<List<RoomResponse>> getAllRoom(){
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/room/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roomService.getById(id), HttpStatus.OK);
    }
}
