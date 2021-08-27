package com.rideguide.request.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "DriverInformationParam", description = "Parameters required to DriverInformation")
@Accessors(chain = true)
@Setter
@Getter
public class DriverInformationDto {
	
	@ApiModelProperty(notes = "UserId", required = true)
	@NotBlank(message = "UserId is required")
	private Long userId;
	
	@ApiModelProperty(notes = "FirstName", required = true)
	@NotBlank(message = "FirstName is required")
	private String firstName;
    
	@ApiModelProperty(notes = "MiddleName")
    private String middleName;
    
	@ApiModelProperty(notes = "LastName", required = true)
	@NotBlank(message = "LastName is required")
    private String lastName;
    
	@ApiModelProperty(notes = "DriversAddress", required = true)
	@NotBlank(message = "DriversAddress is required")
    private String driversAddress;
    
	@ApiModelProperty(notes = "DateOfBirth", required = true)
	@NotBlank(message = "DateOfBirth is required")
    private Date DateOfBirth;
    
	@ApiModelProperty(notes = "Gender", required = true)
	@NotBlank(message = "Gender is required")
    private String gender;
    
	@ApiModelProperty(notes = "Relation", required = true)
	@NotBlank(message = "Relation is required")
    private String Relation;
    
	@ApiModelProperty(notes = "DriversLicenseIdNo", required = true)
	@NotBlank(message = "DriversLicenseIdNo is required")
    private String driversLicenseIdNo;
    
	@ApiModelProperty(notes = "DriversLicenseExpiration", required = true)
	@NotBlank(message = "DriversLicenseExpiration is required")
    private Date driversLicenseExpiration;
    
	@ApiModelProperty(notes = "UploadPhoto")
	@NotBlank(message = "UploadPhoto is required")
    private String uploadPhoto;
    
    
	
}