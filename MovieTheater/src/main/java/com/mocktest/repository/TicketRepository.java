package com.mocktest.repository;
import com.mocktest.bean.response.BookedAndCancelTicketResponse;
import com.mocktest.bean.response.BookingListResponse;
import com.mocktest.bean.response.ConfirmTicketResponse;
import com.mocktest.entities.Ticket;
import com.mocktest.entities.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("FROM Ticket t " +
            "JOIN ShowTime s ON s.id = t.showTime.id " +
            "WHERE s.id = :showTimeId AND t.endTime > :now")
    List<Ticket> getAllSeatBookedInTicket(Long showTimeId, LocalTime now);
    @Transactional
    @Modifying
    @Query("UPDATE Ticket t " +
            "SET t.ticketType = :ticketType " +
            "WHERE t.id = :id")
    void UpdateStatusTicket(@Param("id") Long id, @Param("ticketType") TicketStatus ticketType);
    @Query("SELECT new com.mocktest.bean.response.BookedAndCancelTicketResponse(m.movieNameVN, SUM(s2.price), t.createdDate ,t.ticketType) " +
            "FROM Ticket t " +
            "INNER JOIN t.showTime s " +
            "INNER JOIN s.movie m " +
            "INNER JOIN t.seat s2 " +
            "WHERE t.user.username = :username " +
            "GROUP BY m.movieNameVN, t.createdDate, t.ticketType")
    List<BookedAndCancelTicketResponse> getTotalPriceByTicket(String username);

    @Query("SELECT new com.mocktest.bean.response.BookingListResponse(t.id, t.user.userId, u.fullName, u.identityCard, u.phone, m.movieNameVN, t.startTime, s2.seatColumn, s2.seatRow, t.createdDate,t.ticketType) " +
            "FROM Ticket t " +
            "INNER JOIN t.user u " +
            "INNER JOIN t.showTime s " +
            "INNER JOIN s.movie m " +
            "INNER JOIN t.seat s2 " +
            "WHERE m.Active = 'true'")
    List<BookingListResponse> getAllBookingUser();
    @Query("SELECT new com.mocktest.bean.response.BookingListResponse(t.id, t.user.userId, u.fullName, u.identityCard, u.phone, m.movieNameVN, t.startTime, s2.seatColumn, s2.seatRow, t.createdDate,t.ticketType) " +
            "FROM Ticket t " +
            "INNER JOIN t.user u " +
            "INNER JOIN t.showTime s " +
            "INNER JOIN s.movie m " +
            "INNER JOIN t.seat s2 " +
            "WHERE u.username = :data OR u.phone = :data OR u.identityCard = :data OR u.fullName = :data")
    List<BookingListResponse> SearchAllBookingUser(String data);
    @Query("SELECT new com.mocktest.bean.response.ConfirmTicketResponse" +
            "( t.id, r.roomName,m.movieNameVN, m.movieNameEnglish,t.createdDate, t.startTime, s2.seatColumn," +
            " s2.seatRow, s2.price, u.username, u.fullName, u.identityCard, u.phone) \n" +
            "FROM Ticket t \n" +
            "INNER JOIN ShowTime s ON s.id = t.showTime.id \n" +
            "INNER JOIN Room r ON r.id = s.room.id \n" +
            "INNER JOIN Movie m ON m.id = s.movie.id \n" +
            "INNER JOIN User u ON u.userId = t.user.userId \n" +
            "INNER JOIN Seat s2 ON s2.id = t.seat.id \n" +
            "WHERE t.id = :id")
    ConfirmTicketResponse getTicketById(Long id);
    @Query("FROM Ticket t " +
            "WHERE t.showTime.id = :id AND t.endTime > :now")
    Ticket getTicketActive(Long id, LocalTime now);
}
