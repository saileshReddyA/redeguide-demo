package com.rideguide.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "driver_information")
@Data
@EqualsAndHashCode(callSuper = true)
public class DriverInformation extends BaseEntity {
	
	public static enum Relation  { SELF, FAMILY_MEMBER}
	public static enum Gender  { M, F}
	
	@Column(name = "User_Id")
    private String userId;
	
    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Middle_Name")
    private String middleName;
    
    @Column(name = "Last_Name")
    private String lastName;
    
    
    @Column(name = "Drivers_Address")
    private String driversAddress;
    
    @Column(name = "Date_Of_Birth")
    //@Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "Gender")
   // private String gender;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
   
    @Column(name = "Relation")
    //@Enumerated(EnumType.STRING)
    private String relation;
    
    @Column(name = "Drivers_License_Id_No")
    private String driversLicenseIdNo;
    
    @Column(name = "Drivers_License_Expiration")
    //@Temporal(TemporalType.DATE)
    private Date driversLicenseExpiration;
    
    @Column(name = "Upload_Photo")
    private String uploadPhoto;

}
