package com.learnwithankit.employeeservice.controller;

import com.learnwithankit.employeeservice.dto.APIResponseDto;
import com.learnwithankit.employeeservice.dto.EmployeeDto;
import com.learnwithankit.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
@Tag(
        name = "Employee Service - EmployeeController",
        description = "EmployeeController exposes REST APIs for Employee Service"
)
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    @Operation(
            summary = "Save Employee REST API",
            description = "Save Employee REST API is used to save Employee into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 200 CREATED"
    )
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get Employee REST API",
            description = "Get Employee REST API is used to get an Employee object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 OK"
    )
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
