package com.learnwithankit.departmentservice.controller;

import com.learnwithankit.departmentservice.dto.DepartmentDto;
import com.learnwithankit.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/department")
@Tag( //for api documentation
        name = "Department Service - DepartmentController",
        description = "Department Controller exposes REST APIs for Department-Service"
)
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    @Operation( //for api documentation
            summary = "Save department REST API",
            description = "Save department REST API is used to save department in database"
    )
    @ApiResponse( //for api documentation
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    @Operation(
            summary = "Get department REST API",
            description = "Get department REST API is used to get department from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
