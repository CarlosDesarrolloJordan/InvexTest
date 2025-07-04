package com.empresa.empleado.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.empleado.dto.EmployeeDto;
import com.empresa.empleado.dto.EmployeeListWrapper;
import com.empresa.empleado.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "bearerAuth")
@Validated
@RestController
@RequestMapping("/employees")
@Tag(name = "Employee API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll() { return employeeService.findAll(); }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) { return employeeService.findById(id); }

    @PostMapping
    public ResponseEntity<List<EmployeeDto>> saveAll(@Valid @RequestBody EmployeeListWrapper wrapper) {
   
        List<EmployeeDto> saved = employeeService.saveAll(wrapper.getEmployees());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@Valid @PathVariable Long id, @Valid @RequestBody EmployeeDto employee) {
        EmployeeDto updated = employeeService.update(id, employee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @GetMapping("/search")
    public List<EmployeeDto> searchByName(@RequestParam String name) { 
        return employeeService.searchByName(name);
    }
}
