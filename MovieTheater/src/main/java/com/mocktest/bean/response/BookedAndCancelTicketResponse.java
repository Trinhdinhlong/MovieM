package com.mocktest.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Seat;
import com.mocktest.entities.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.http.HttpHeaders;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookedAndCancelTicketResponse {
    private String movieNameVN;
    private double totalAmount;
    private LocalDateTime startDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketType;
}
