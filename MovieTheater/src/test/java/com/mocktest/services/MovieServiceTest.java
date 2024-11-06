package com.mocktest.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private static MovieService movieService;
    @BeforeAll
    public static void setup() {
        movieService = new MovieService();
    }

    @Test
    void getAll() {
    }

//    @Test
//    void testGetAll() {
//        String date = "13-03-2024 00:00";
//        movieService.getAll(date);
//    }
}