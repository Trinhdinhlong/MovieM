package com.mocktest.repository;

import com.mocktest.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
@Query("FROM Seat s " +
        "INNER JOIN Room r ON r.id = s.room.id " +
        "WHERE r.id = :roomId")
List<Seat> getAllSeatsByRoom(Long roomId);




}
