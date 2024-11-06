package com.mocktest.services;

import com.mocktest.entities.Role;
import com.mocktest.exceptions.ErrorCode;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public  Role getById(Long id) {
            Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_ROLE_NOT_FOUND));
            return role;
    }
    public Role create(Role request) {
            return roleRepository.save(request);
    }
    public Role updateById(Role request, Long id) {
            Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_ROLE_NOT_FOUND));
            BeanUtils.copyProperties(request, role);
            return roleRepository.save(role);
    }
    public void deleteById(Long request) {
            if (roleRepository.existsById(request)) {
                roleRepository.deleteById(request);
            } else {
                throw new NotFoundException(ErrorCode.ERROR_ROLE_NOT_FOUND);
            }
    }
}
