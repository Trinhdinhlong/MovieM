package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "seats", schema = "dbo")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;
    @Column(name = "seat_column", length = 2)
    private String seatColumn;
    @Column(name = "seat_row")
    private int seatRow;
    @Column(name = "price")
    private Double price;
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    @Column(name = "seatType")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    @JsonBackReference
    private Room room;
    @JsonIgnore
    @OneToMany(mappedBy = "seat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatColumn='" + seatColumn + '\'' +
                ", seatRow=" + seatRow +
                ", price=" + price +
                ", seatType=" + seatType +
                '}';
    }
}
