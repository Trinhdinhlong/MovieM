package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies", schema = "dbo")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "movie_name_english")
    private String movieNameEnglish;
    @Column(name = "movie_name_vn")
    private String movieNameVN;
    @Column(name = "actor")
    private String actor;
    @Column(name = "director")
    private String director;
    @Column(name = "duration")
    private int duration;
    @Column(name = "movie_production_company")
    private String movieProductionCompany;
    @Column(name = "started_date")
    private LocalDate startedDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "image_url")
    private String imageURL;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    @Column(name = "version")
    private String version;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_type",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<TypeMovie> typeMovies = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShowTime> showTimes;
    @Column(name = "isActive")
    private String Active = "true";

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", movieNameEnglish='" + movieNameEnglish + '\'' +
                ", movieNameVN='" + movieNameVN + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", movieProductionCompany='" + movieProductionCompany + '\'' +
                ", startedDate=" + startedDate +
                ", endDate=" + endDate +
                ", imageURL='" + imageURL + '\'' +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", version='" + version + '\'' +
                ", typeMovies=" + typeMovies +
                ", Active='" + Active + '\'' +
                '}';
    }
}
