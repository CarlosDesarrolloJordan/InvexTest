package com.empresa.empleado;

import com.empresa.empleado.dto.EmployeeDto;
import com.empresa.empleado.entity.Employee;
import com.empresa.empleado.exception.EmployeeNotFoundException;
import com.empresa.empleado.mapper.EmployeeMapper;
import com.empresa.empleado.repository.EmployeeRepository;
import com.empresa.empleado.service.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Alex");
        employee.setLastName("Ramirez");
        employee.setAge(30);
        employee.setGender("M");
        employee.setBirthDate(LocalDate.of(1993, 5, 12));
        employee.setActive(true);

        employeeDto = EmployeeMapper.toDto(employee);
    }

    @Test
    void testFindAll() {
        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));
        List<EmployeeDto> result = employeeService.findAll();
        assertEquals(1, result.size());
        assertEquals("Alex", result.get(0).getFirstName());
    }

    @Test
    void testFindById_found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        EmployeeDto result = employeeService.findById(1L);
        assertEquals("Alex", result.getFirstName());
    }

    @Test
    void testFindById_notFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findById(99L));
    }

    @Test
    void testSaveAll() {
        List<EmployeeDto> dtos = Collections.singletonList(employeeDto);
        when(employeeRepository.saveAll(anyList())).thenReturn(Collections.singletonList(employee));
        List<EmployeeDto> result = employeeService.saveAll(dtos);
        assertEquals(1, result.size());
        assertEquals("Alex", result.get(0).getFirstName());
    }

    @Test
    void testUpdate_found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employeeDto.setFirstName("Modificado");
        EmployeeDto updated = employeeService.update(1L, employeeDto);

        assertEquals("Modificado", updated.getFirstName());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void testUpdate_notFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        employeeDto.setId(2L);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(1L, employeeDto));
    }

    @Test
    void testDelete_found() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(1L);
        assertDoesNotThrow(() -> employeeService.delete(1L));
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        when(employeeRepository.existsById(2L)).thenReturn(false);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete(2L));
    }

    @Test
    void testSearchByName() {
        when(employeeRepository.findByFirstNameContainingIgnoreCase("alex")).thenReturn(Collections.singletonList(employee));
        List<EmployeeDto> result = employeeService.searchByName("alex");
        assertEquals(1, result.size());
        assertEquals("Alex", result.get(0).getFirstName());
    }
}