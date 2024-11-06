package com.mocktest.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfirmTicketResponse {
    private Long ticketId;
    private String room_name;
    private String movieNameVN;
    private String movieNameEN;
    private LocalDateTime startDate;
    private LocalTime start_time;
    private String seatColumn;
    private int seatRow;
    private Double price;
    private String username;
    private String fullName;
    private String identityCard;
    private String phone;
}
