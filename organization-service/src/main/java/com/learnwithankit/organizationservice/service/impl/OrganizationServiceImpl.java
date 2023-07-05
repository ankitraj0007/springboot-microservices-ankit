package com.learnwithankit.organizationservice.service.impl;

import com.learnwithankit.organizationservice.dto.OrganizationDto;
import com.learnwithankit.organizationservice.entity.Organization;
import com.learnwithankit.organizationservice.mapper.OrganizationMapper;
import com.learnwithankit.organizationservice.repository.OrganizationRepository;
import com.learnwithankit.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        OrganizationDto savedOrganizationDto = OrganizationMapper.mapToOrganizationDto(savedOrganization);
        return savedOrganizationDto;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization);
        return organizationDto;
    }
}
