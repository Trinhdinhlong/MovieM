package com.mocktest.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingTicketResponse {
     private String username;
     private LocalDateTime createdTime;
     private LocalTime startTime;
     private String seatColumn;
     private int seatRow;
     @Enumerated(EnumType.STRING)
     private SeatType seatType;
     private Double price;

}
