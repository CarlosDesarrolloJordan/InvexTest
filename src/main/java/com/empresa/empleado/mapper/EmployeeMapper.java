package com.empresa.empleado.mapper;

import com.empresa.empleado.dto.EmployeeDto;
import com.empresa.empleado.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee entity) {
        if (entity == null) return null;

        EmployeeDto dto = new EmployeeDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setSecondLastName(entity.getSecondLastName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setBirthDate(entity.getBirthDate());
        dto.setPosition(entity.getPosition());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setActive(entity.getActive());
        return dto;
    }

    public static Employee toEntity(EmployeeDto dto) {
        if (dto == null) return null;

        Employee entity = new Employee();
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setSecondLastName(dto.getSecondLastName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPosition(dto.getPosition());
        entity.setActive(dto.getActive() != null ? dto.getActive() : true);
        return entity;
    }

}
