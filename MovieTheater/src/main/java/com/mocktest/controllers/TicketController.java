package com.mocktest.controllers;
import com.mocktest.bean.request.BookingTicketRequest;
import com.mocktest.bean.response.*;
import com.mocktest.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;
    @PostMapping("ticket/booking")
    public ResponseEntity<List<BookingTicketResponse>> saveAllBooking
            (@RequestBody List<BookingTicketRequest> bookingTicketRequest){
         return new ResponseEntity<>(ticketService.saveTicket(bookingTicketRequest), HttpStatus.OK);
    }
    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketStatusResponse> UpdateStatusTicket(@PathVariable("id") Long id){
        ticketService.UpdateStatusTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/ticket/admin/{id}")
    public ResponseEntity<ConfirmTicketResponse> getConfirmByAdmin(@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.getConfirmAdminByTicketId(id), HttpStatus.OK);
    }
    @GetMapping("ticket/booked/{username}")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasBookedANDGotten
            (@PathVariable("username") String username){
        return new ResponseEntity<> (ticketService.getAllBookedList(username), HttpStatus.OK);
    }
    @GetMapping("ticket/search/{data}")
    public ResponseEntity<List<BookingListResponse>> getAllBookingListByUserId(@PathVariable("data") String data){
        return new ResponseEntity<>(ticketService.searchAllBookingUserByUserName(data), HttpStatus.OK);
    }
    @GetMapping("/ticket/cancel/{username}")
    public ResponseEntity<List<BookedAndCancelTicketResponse>> getAllTicketHasAbort
            (@PathVariable("username") String username){
        return new ResponseEntity<>(ticketService.getAllCancelList(username), HttpStatus.OK);
    }
    @GetMapping("/ticket/history/{username}")
    public ResponseEntity<List<HistoryTicketResponse>> getAllTicketHistory
            (@PathVariable("username") String username){
        return new ResponseEntity<>(ticketService.getAllHistoryList(username), HttpStatus.OK);
    }
    @GetMapping("/ticket/booking")
    public ResponseEntity<List<BookingListResponse>> getAllBookingByAdmin(){
        return new ResponseEntity<>(ticketService.getAllBookingUser(), HttpStatus.OK);

    }
}
