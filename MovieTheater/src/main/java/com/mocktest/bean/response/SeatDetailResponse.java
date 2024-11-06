package com.mocktest.bean.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatDetailResponse {
    private Long id;
    private String seatColumn;
    private int seatRow;
    private Double price;
    private boolean isAvailable = true;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
