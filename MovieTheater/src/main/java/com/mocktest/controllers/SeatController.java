package com.mocktest.controllers;

import com.mocktest.bean.request.SeatRequest;
import com.mocktest.bean.response.SeatTypeResponse;
import com.mocktest.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SeatController {
    private final SeatService seatService;
    @GetMapping("/movie/{movieId}/room/{roomId}/showtime/{showTimeId}/seats")
    public ResponseEntity<Object> getAllSeatByMovie(@PathVariable("movieId") Long movieId,
                                                    @PathVariable("roomId") Long roomId,
                                                    @PathVariable("showTimeId") Long showTimeId){
        return new ResponseEntity<>(seatService.getAllSeatByMovieAndRoom(roomId,movieId, showTimeId), HttpStatus.OK);
    }
    @PutMapping("/seats")
    public ResponseEntity<List<SeatTypeResponse>> updateStatusSeat(@RequestBody List<SeatRequest> request){
        return new ResponseEntity<>( seatService.updateTypeSeatById(request), HttpStatus.OK);
    }
    @GetMapping("/room/{id}/seats")
    public ResponseEntity<List<SeatTypeResponse>> getAllSeatByRoom(@PathVariable("id") Long id){
        return new ResponseEntity<>(seatService.getAllSeatByRoom(id), HttpStatus.OK);
    }
}
