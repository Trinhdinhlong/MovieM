package com.mocktest.repository;

import com.mocktest.entities.TypeMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeMovie, Long> {
}
