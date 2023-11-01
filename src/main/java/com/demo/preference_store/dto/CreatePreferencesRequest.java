package com.demo.preference_store.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePreferencesRequest {
    private String tenantId;
    private String preferenceType;
    int schemaVersion;
    private String owner;
}
