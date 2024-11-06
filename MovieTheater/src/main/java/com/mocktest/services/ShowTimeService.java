package com.mocktest.services;

import com.mocktest.bean.request.MovieRequest;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Room;
import com.mocktest.entities.ShowTime;
import com.mocktest.exceptions.ErrorCode;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import com.mocktest.repository.RoomRepository;
import com.mocktest.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<ShowTime> createShowTime(MovieRequest request, Movie movie) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_ROOM_NOT_FOUND));
        Movie movieFromDb = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_MOVIE_NOT_FOUND));
        List<LocalTime> startTimes = request.getStartTime();
        return startTimes.stream().map(startTime -> {
             return showTimeRepository.save(
                    ShowTime.builder()
                            .room(room)
                            .movie(movieFromDb)
                            .startTime(startTime)
                            .endTime(startTime.plusMinutes(request.getDuration()))
                            .Active("true")
                            .build()
            );
        }).collect(Collectors.toList());
    }

    public List<ShowTime> getAllShowTime() {
        return showTimeRepository.findAll();
    }

    public ShowTime getById(Long showTimeId) {
        ShowTime request = showTimeRepository.findById(showTimeId).orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_SHOWTIME_NOT_FOUND));
        return  request;
    }
    public void DeleteById(Long id){
        if (showTimeRepository.existsById(id)) {
            ShowTime showTime = showTimeRepository.getById(id);
            showTime.setActive("false");
            showTimeRepository.save(showTime);
        }else {
            throw new NotFoundException(ErrorCode.ERROR_SHOWTIME_NOT_FOUND);
        }
    }
    public List<ShowTime> getAllShowTimeById(Long id){
        return showTimeRepository.getAllShowTimeById(id);
    }
    public Long getDuration(Long showTimeId) {
        return showTimeRepository.getDurationMovieInShowTime(showTimeId);
    }
}
