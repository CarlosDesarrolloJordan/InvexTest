package com.empresa.empleado.service;

import java.util.List;

import com.empresa.empleado.dto.EmployeeDto;

public interface EmployeeService {

    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
    List<EmployeeDto> saveAll(List<EmployeeDto> dtos);
    EmployeeDto update(Long id, EmployeeDto dto);
    void delete(Long id);
    List<EmployeeDto> searchByName(String name);
}
