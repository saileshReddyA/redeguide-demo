package com.rideguide.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity(name = "user_password_reset_request")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordResetRequest extends BaseEntity {
	
	@Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private String externalId;
	
    @Column(name = "user_id")
    private String userId;

    @Column(name = "expire_date_time")
    private Timestamp expireDateTime;

}
