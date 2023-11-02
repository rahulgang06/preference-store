package com.demo.preference_store.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePreferenceRequest {
    String tenantId;
    String preferenceType;
    String owner;
    byte[] data;
    // ToDo add resourceId, description;
}
