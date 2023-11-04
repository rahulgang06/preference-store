package com.demo.preference_store.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PreferenceResponse {
    private String tenantId;
    private String preferenceType;
    private String resourceID;
    int schemaVersion;
    private String owner;
    private String status;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String updatedBy;
}
