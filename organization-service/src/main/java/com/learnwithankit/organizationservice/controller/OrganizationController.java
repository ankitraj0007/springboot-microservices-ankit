package com.learnwithankit.organizationservice.controller;

import com.learnwithankit.organizationservice.dto.OrganizationDto;
import com.learnwithankit.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/organization")
@Tag( //for api documentation
        name = "Organization Service - DOrganizationController",
        description = "Organization Controller exposes REST APIs for Organization-Service"
)
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    @Operation( //for api documentation
            summary = "Save Organization REST API",
            description = "Save Organization REST API is used to save Organization in database"
    )
    @ApiResponse( //for api documentation
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    public ResponseEntity<OrganizationDto> saveOrganizationDto(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);

    }

    @GetMapping("{code}")
    @Operation(
            summary = "Get Organization REST API",
            description = "Get Organization REST API is used to get a single Organization using code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }
}
