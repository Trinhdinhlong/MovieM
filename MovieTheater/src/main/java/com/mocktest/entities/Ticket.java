package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tickets", schema = "dbo")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketType;

    @Column(name = "start_time")
    private LocalTime startTime;
    @JsonIgnore
    @Column(name = "end_time")
    private LocalTime endTime;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
    private Seat seat;

    @ManyToOne
//    @JsonBackReference
    @JoinColumn(name = "showtime_id", referencedColumnName = "showtime_id")
    private ShowTime showTime;

    @ManyToOne
//    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType=" + ticketType +
                ", startTime=" + startTime +
                ", createdDate=" + createdDate +
                ", seat=" + seat +
                ", showTime=" + showTime +
                ", user=" + user +
                '}';
    }
}
