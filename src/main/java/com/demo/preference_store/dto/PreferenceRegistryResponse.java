package com.demo.preference_store.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class PreferenceRegistryResponse {
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
