package com.mocktest.bean.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.TicketStatus;
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
public class BookingListResponse {
       private Long ticketId;
       private Long userId;
       private String fullName;
       private String identityCard;
       private String phoneNumber;
       private String movieNameVN;
       private LocalTime startTime;
       private String seatColumn;
       private int seatRow;
       LocalDateTime createdDate;
       @Enumerated(EnumType.STRING)
       private TicketStatus ticketType;
}
