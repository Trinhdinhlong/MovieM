package com.mocktest.controllers;

import com.mocktest.bean.response.MovieShowTimeResponse;
import com.mocktest.bean.request.MovieRequest;
import com.mocktest.bean.response.MovieResponse;
import com.mocktest.bean.response.MovieWithCategoryResponse;
import com.mocktest.entities.TypeMovie;
import com.mocktest.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/movies")
    public ResponseEntity<List<MovieWithCategoryResponse>> getAllMoviesByCategories() {
        return new ResponseEntity<>(movieService.getAllByCategories(), HttpStatus.OK);
    }
    @GetMapping("/movies/showtime")
    public ResponseEntity<?> getAllMovie(){
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }
    @GetMapping("movies/showtime/{movieName}")
    public ResponseEntity<List<MovieShowTimeResponse>> getAllMovieById(@PathVariable("movieName") String movieName){
        return new ResponseEntity<>(movieService.getAll(movieName), HttpStatus.OK);
    }
    @GetMapping("/type")
    public ResponseEntity<?> getMovieType() {
        List<TypeMovie> res = movieService.getMovieTypes();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/movies/admin")
    public ResponseEntity<List<MovieResponse>> getAllMovieByAdmin(){
        List<MovieResponse> movieResponses = movieService.getAllMovieByAdmin();
        return new ResponseEntity<>(movieResponses, HttpStatus.OK);
    }
    @GetMapping("/movie/admin/{movieId}")
    public ResponseEntity<MovieResponse> getAllMovieByAdminAndMovieId(@PathVariable("movieId") Long movieId){
        return new ResponseEntity<>(movieService.getById(movieId), HttpStatus.OK);
    }
    @GetMapping("movie/{typeName}")
    public ResponseEntity<List<MovieWithCategoryResponse>> getAllMovieByTypeName(@PathVariable("typeName") String typeName){
        return new ResponseEntity<>(movieService.getAllByCategoriesByType(typeName), HttpStatus.OK);
    }
    @GetMapping("/movies/admin/{data}")
    public ResponseEntity<MovieResponse> SearchMovieByMovieName(@PathVariable("data") String data){
        MovieResponse responses = movieService.searchMovieByMovieName(data);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long request) {
        movieService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }
    @PostMapping("/movie")
    public ResponseEntity<?> createMovie(@RequestBody MovieRequest request) {
        MovieResponse newMovie = movieService.create(request);
        return new ResponseEntity<>(newMovie, HttpStatus.OK);
    }

    @PutMapping("/movie")
    public ResponseEntity<MovieResponse> updateMovie(@RequestBody MovieRequest request){
        System.out.println(request);
        return new ResponseEntity<>(movieService.UpdateMovie(request), HttpStatus.OK);
//        return null;
    }

}

