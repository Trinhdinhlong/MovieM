package com.mocktest.repository;

import com.mocktest.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    Boolean existsByIdAndMovieIdAndRoomId(Long showTimeId, Long movieId, Long roomId);
//    Boolean existsByStartTimeAndRoomIdAndActive(LocalTime startTime, Long roomId, String active);
    @Query("SELECT m.duration \n" +
            "FROM ShowTime s \n" +
            "INNER JOIN Movie m ON m.id = s.movie.id\n" +
            "WHERE s.id = :id AND m.Active = 'true'\n")
    Long getDurationMovieInShowTime(Long id);
    @Query("FROM ShowTime s " +
            "INNER JOIN Movie m ON m.id = s.movie.id " +
            "WHERE m.id = :id AND s.Active = 'true'")
    List<ShowTime> getAllShowTimeById(Long id);
    @Query("SELECT s FROM ShowTime s WHERE s.startTime > :now AND s.Active = 'true'")
    List<ShowTime> findAllAfterCurrentTime(LocalTime now);
    @Query("SELECT s FROM ShowTime s " +
            "INNER JOIN Movie m ON m.id = s.movie.id " +
            "WHERE s.startTime > :now AND s.Active = 'true' AND s.movie.id = :id")
    List<ShowTime> findAllAfterCurrentTimeByMovieId( LocalTime now, Long id);
}
