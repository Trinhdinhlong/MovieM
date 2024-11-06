package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "showtimes", schema = "dbo")
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "showTime", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    @JsonBackReference
    private Room room;
    @Column(name = "isActive")
    private String Active = "true";

    @Override
    public String toString() {
        return "ShowTime{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", Active='" + Active + '\'' +
                '}';
    }
//    @Override
//    public String toString() {
//        return "ShowTime{" +
//                "id=" + id +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", movie=" + movie +
//                '}';
//    }
}
