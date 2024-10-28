package com.acme.vortex.platform.shared.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Getter
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    @Getter
    private Date updatedAt;
}
