package com.demo.preference_store.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preference {
    long id;
    String tenantId;
    String preferenceType;
    String resourceId;
    int schemaVersion;
    String owner;
    String status;
    String description;
    byte[] data; // Blob in mysql
    Timestamp createdAt;
    Timestamp updatedAt;
    String createdBy;
    String updatedBy;

    // Unique index: tenantId, owner, resourceId, preferenceType
}
