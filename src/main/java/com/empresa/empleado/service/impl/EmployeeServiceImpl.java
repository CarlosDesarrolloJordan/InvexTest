package com.empresa.empleado.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.empleado.dto.EmployeeDto;
import com.empresa.empleado.entity.Employee;
import com.empresa.empleado.exception.EmployeeNotFoundException;
import com.empresa.empleado.mapper.EmployeeMapper;
import com.empresa.empleado.repository.EmployeeRepository;
import com.empresa.empleado.service.EmployeeService;
import com.empresa.empleado.util.Messages;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(Messages.EMPLOYEE_NOT_FOUND + id));
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> saveAll(List<EmployeeDto> dtos) {

       List<Long> exstingLong = dtos.stream()
        .map(EmployeeDto::getId)
        .filter(Objects::nonNull)
        .filter(employeeRepository::existsById)
        .collect(Collectors.toList());


        if(!exstingLong.isEmpty()){
            throw new EmployeeNotFoundException("The id already exists");
        }
        List<Employee> entities = dtos.stream()
                .map(EmployeeMapper::toEntity)
                .collect(Collectors.toList());

        List<Employee> savedEntities = employeeRepository.saveAll(entities);

        return savedEntities.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto dto) {
        if (id == null) {
            throw new IllegalArgumentException(Messages.EMPLOYEE_ID_NULL);
        }
    
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(Messages.NO_UPDATE + Messages.EMPLOYEE_NOT_FOUND + id));
    
        // Map de actualizaciones: si el valor en el DTO no es null, se aplica al entity
        List<Runnable> updates = List.of(
            () -> Optional.ofNullable(dto.getFirstName()).ifPresent(existing::setFirstName),
            () -> Optional.ofNullable(dto.getMiddleName()).ifPresent(existing::setMiddleName),
            () -> Optional.ofNullable(dto.getLastName()).ifPresent(existing::setLastName),
            () -> Optional.ofNullable(dto.getSecondLastName()).ifPresent(existing::setSecondLastName),
            () -> Optional.ofNullable(dto.getAge()).ifPresent(existing::setAge),
            () -> Optional.ofNullable(dto.getGender()).ifPresent(existing::setGender),
            () -> Optional.ofNullable(dto.getBirthDate()).ifPresent(existing::setBirthDate),
            () -> Optional.ofNullable(dto.getPosition()).ifPresent(existing::setPosition),
            () -> Optional.ofNullable(dto.getActive()).ifPresent(existing::setActive)
        );
    
        updates.forEach(Runnable::run);
    
        Employee updated = employeeRepository.save(existing);
        return EmployeeMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(Messages.NO_DELETE + Messages.EMPLOYEE_NOT_FOUND + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> searchByName(String name) {
        List<Employee> results = employeeRepository.findByFirstNameContainingIgnoreCase(name);
        return results.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList())      ;
    }
}
