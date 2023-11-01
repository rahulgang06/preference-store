package com.demo.preference_store.service;

import com.demo.preference_store.dto.CreatePreferencesRequest;
import com.demo.preference_store.dto.PreferencesResponse;
import com.demo.preference_store.entity.Preferences;
import com.demo.preference_store.repository.PreferencesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PreferencesMgmtService {


    PreferencesRepository repository;

    public PreferencesResponse createUser(CreatePreferencesRequest request){
        Preferences preferences = repository.createUserPreferences(convertCreatePreferencesRequest(request));
        return convertPreferencesToInfoResponse(preferences);
    }
    public PreferencesResponse updateUser(CreatePreferencesRequest request){
        Preferences preferences = repository.updateUserPreferences(convertCreatePreferencesRequest(request));
        return convertPreferencesToInfoResponse(preferences);
    }


    private PreferencesResponse convertPreferencesToInfoResponse(Preferences user) {
        PreferencesResponse response = new PreferencesResponse();
        response.setTenantId(user.getTenantId());
        response.setPreferenceType(user.getPreferenceType());
        response.setResourceID(user.getResourceID());
        response.setOwner(user.getOwner());
        response.setSchemaVersion(user.getSchemaVersion());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
    private Preferences convertCreatePreferencesRequest(CreatePreferencesRequest request) {
        Preferences user = new Preferences();
        user.setTenantId(request.getTenantId());
        user.setPreferenceType(request.getPreferenceType());
        user.setStatus("ACTIVE");
        user.setOwner(request.getOwner());
        user.setResourceID(UUID.randomUUID()
                .toString());
        user.setSchemaVersion(request.getSchemaVersion());
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }
}
