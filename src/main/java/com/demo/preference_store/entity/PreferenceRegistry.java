package com.demo.preference_store.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class PreferenceRegistry {
    private long id;
    private String tenantId;
    private String preferenceType;
    int schemaVersion;
    private String status;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String updatedBy;


}

