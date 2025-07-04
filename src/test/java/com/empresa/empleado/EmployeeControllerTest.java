package com.empresa.empleado;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.empresa.empleado.controller.EmployeeController;
import com.empresa.empleado.dto.EmployeeDto;
import com.empresa.empleado.dto.EmployeeListWrapper;
import com.empresa.empleado.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = EmployeeServiceApplication.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private EmployeeDto employee1;
    private EmployeeDto employee2;

    @BeforeEach
    public void setup() {
        employee1 = new EmployeeDto();
        employee1.setId(1L);
        employee1.setFirstName("Carlos");
        employee1.setLastName("Gonzalez");
        employee1.setAge(30);
        employee1.setGender("M");
        employee1.setBirthDate(LocalDate.of(1993, 5, 12));
        employee1.setActive(true);

        employee2 = new EmployeeDto();
        employee2.setId(2L);
        employee2.setFirstName("Maria");
        employee2.setLastName("Lopez");
        employee2.setAge(28);
        employee2.setGender("F");
        employee2.setBirthDate(LocalDate.of(1995, 3, 22));
        employee2.setActive(true);
    }

    @Test
    public void testFindAll() throws Exception {
        List<EmployeeDto> employees = Arrays.asList(employee1, employee2);
        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Carlos"));
    }

    @Test
    public void testFindById() throws Exception {
        when(employeeService.findById(1L)).thenReturn(employee1);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Carlos"));
    }

    @Test
    public void testSaveAll() throws Exception {
        List<EmployeeDto> inputList = Arrays.asList(employee1, employee2);

        // Envolver la lista en el wrapper
        EmployeeListWrapper wrapper = new EmployeeListWrapper();
        wrapper.setEmployees(inputList);

        when(employeeService.saveAll(anyList())).thenReturn(inputList);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrapper)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Maria"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(employeeService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testSearchByName() throws Exception {
        when(employeeService.searchByName("Car")).thenReturn(List.of(employee1));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/search")
                        .param("name", "Car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Carlos"));
    }


}