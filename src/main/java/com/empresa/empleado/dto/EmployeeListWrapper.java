package com.empresa.empleado.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class EmployeeListWrapper implements Serializable{
   
    @Valid
    @NotEmpty(message = "Employees list cannot be empty")
    private List<EmployeeDto> employees;

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}
