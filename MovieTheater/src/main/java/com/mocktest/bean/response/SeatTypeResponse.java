package com.mocktest.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatTypeResponse {
    private Long id;
    private String seatColumn;
    private int seatRow;
    private SeatType seatType;
}
