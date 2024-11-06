package com.mocktest.bean.request;

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
public class SeatRequest {
    private Long id;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
