package com.learnwithankit.employeeservice.service;

import com.learnwithankit.employeeservice.dto.APIResponseDto;
import com.learnwithankit.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
