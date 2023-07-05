package com.learnwithankit.employeeservice.service;

import com.learnwithankit.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // with this eureka server load balancer will provide available service/port running
//open feign APIClient
public interface APIClient {

    @GetMapping("api/department/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
