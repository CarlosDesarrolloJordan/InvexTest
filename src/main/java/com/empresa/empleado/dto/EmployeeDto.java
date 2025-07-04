package com.empresa.empleado.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeDto implements Serializable{

    private Long id;

    @NotBlank(message = "First name is required")
@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
private String firstName;

@Size(max = 50, message = "Middle name must not exceed 50 characters")
private String middleName;

@NotBlank(message = "Last name is required")
@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
private String lastName;

@Size(max = 50, message = "Second last name must not exceed 50 characters")
private String secondLastName;

@NotNull(message = "Age is required")
@Min(value = 18, message = "Minimum allowed age is 18")
private Integer age;

@NotBlank(message = "Gender is required")
@Size(max = 10, message = "Gender must not exceed 10 characters")
private String gender;

@NotNull(message = "Birth date is required")
@JsonFormat(pattern = "dd-MM-yyyy")
private LocalDate birthDate;

@Size(max = 100, message = "Position must not exceed 100 characters")
private String position;

private LocalDateTime createdAt;

private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    

}
