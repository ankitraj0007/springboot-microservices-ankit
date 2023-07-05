package com.learnwithankit.employeeservice.service.impl;

import com.learnwithankit.employeeservice.dto.APIResponseDto;
import com.learnwithankit.employeeservice.dto.DepartmentDto;
import com.learnwithankit.employeeservice.dto.EmployeeDto;
import com.learnwithankit.employeeservice.dto.OrganizationDto;
import com.learnwithankit.employeeservice.entity.Employee;
import com.learnwithankit.employeeservice.mapper.EmployeeMapper;
import com.learnwithankit.employeeservice.repository.EmployeeRepository;
import com.learnwithankit.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

//    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        LOGGER.info("inside getEmployeeById method");
        Employee employee = employeeRepository.findById(employeeId).get();

        //API call using RestTemplate (http call)
//        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.getForEntity("http://localhost:8080/api/department/"+employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();

        //API call using WebClient (http call)
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/department/"+employee.getDepartmentCode(), DepartmentDto.class)
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        //API call using spring cloud open feign
//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        //Api call to organization
        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organization/"+ employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {

        LOGGER.info("inside getDefaultDepartment method");
        Employee employee = employeeRepository.findById(employeeId).get();

        //Default department
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and development department (default department)");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}
