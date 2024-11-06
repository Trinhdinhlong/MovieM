package com.mocktest.repository;

import com.mocktest.entities.TypeMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTypeRepository extends JpaRepository<TypeMovie, Long> {
}
