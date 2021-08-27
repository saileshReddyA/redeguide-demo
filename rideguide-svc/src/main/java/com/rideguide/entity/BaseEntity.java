package com.rideguide.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    @CreatedDate
    @Column(name = "created_date_time")
    private Timestamp createdDateTime;

    @LastModifiedDate
    @Column(name = "last_updated_date_time")
    private Timestamp lastUpdatedDateTime;
}
