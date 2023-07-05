package com.learnwithankit.departmentservice.service.impl;

import com.learnwithankit.departmentservice.dto.DepartmentDto;
import com.learnwithankit.departmentservice.entity.Department;
import com.learnwithankit.departmentservice.mapper.DepartmentMapper;
import com.learnwithankit.departmentservice.repository.DepartmentRepository;
import com.learnwithankit.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //convert dept dto to dept jpa entity
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        //convert dept jpa entity to dept dto
        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        //convert dept jpa entity to dept dto
        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}
