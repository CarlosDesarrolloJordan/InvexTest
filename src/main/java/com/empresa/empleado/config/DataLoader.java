package com.empresa.empleado.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.empresa.empleado.entity.Employee;
import com.empresa.empleado.repository.EmployeeRepository;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(EmployeeRepository employeeRepository) {
        return args -> {
            if (employeeRepository.count() == 0) {
                Employee emp1 = new Employee("Carlos", "Adrian", "Jordan", "Ramirez", 30, "M",
                        LocalDate.of(1993, 5, 12), "Developer", true);

                Employee emp2 = new Employee("Ana", "", "Ramírez", "Gómez", 28, "F",
                        LocalDate.of(1995, 2, 8), "Scrum Master", true);

                employeeRepository.saveAll(Arrays.asList(emp1, emp2));
             }
        };
    }
}
