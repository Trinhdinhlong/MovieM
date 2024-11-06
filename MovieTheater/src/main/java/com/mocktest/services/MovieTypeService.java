package com.mocktest.services;

import com.mocktest.entities.TypeMovie;
import com.mocktest.repository.MovieTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieTypeService {
    @Autowired
    private MovieTypeRepository movieTypeRepository;
    public TypeMovie getByTypeId(Long id){
        TypeMovie type = movieTypeRepository.getById(id);
        return type;
    }

}
