package com.rideguide.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "organization")
@Data
@EqualsAndHashCode(callSuper = true)
public class Organization extends BaseEntity {
	
	@Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private String externalId;
	
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "domain")
    private String domain;

    @Column(name = "enabled")
    private boolean enabled;

}
