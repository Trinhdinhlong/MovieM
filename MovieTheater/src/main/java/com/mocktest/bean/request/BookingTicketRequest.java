package com.mocktest.bean.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingTicketRequest {
      private Long seatId;
      private Long showTimeId;
      private Long userId;
}
