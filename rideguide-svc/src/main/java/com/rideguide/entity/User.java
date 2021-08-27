package com.rideguide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rideguide.entity.StringPrefixedSequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Set;

@Entity(name = "user")
@Data
//@EqualsAndHashCode(callSuper = true)
public class User {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "demo_sql")
    @GenericGenerator(name = "demo_sql", strategy = "com.rideguide.entity.StringPrefixedSequenceGenerator", parameters= {
    		         
    	 @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM, value = "1"),
    	 @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "RG_"),
    	 @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%07d")
    })	
	@Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
   
    public static enum Role  { ROLE_SUPER_ADMIN, ROLE_ADMIN, ROLE_USER}
    
    @Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private String externalId;
    
	@Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

   
    @Column(name = "email")
    private String email;

    
    @Column(name = "phone_number")
    private String phoneNumber;

    
    @Column(name = "password")
//    @JsonIgnore
    private String password;

    
    @Column(name = "enabled")
    private boolean enabled;
    
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private Role role;
    
    
    @CreatedDate
    @Column(name = "created_date_time")
    private Timestamp createdDateTime;

    @LastModifiedDate
    @Column(name = "last_updated_date_time")
    private Timestamp lastUpdatedDateTime;

    /*@ManyToMany
    @JoinTable(
            name = "user_organizations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "org_id"))
    private Set<Organization> organizations;
    */

}
