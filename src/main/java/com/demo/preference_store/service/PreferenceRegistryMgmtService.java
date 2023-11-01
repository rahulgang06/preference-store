package com.demo.preference_store.service;

import com.demo.preference_store.dto.CreatePreferenceRegistryRequest;
import com.demo.preference_store.dto.PreferenceRegistryResponse;
import com.demo.preference_store.entity.PreferenceRegistry;
import com.demo.preference_store.repository.PreferenceRegistryRepository;
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
public class PreferenceRegistryMgmtService {
    PreferenceRegistryRepository repository;

    public PreferenceRegistryResponse createUser(CreatePreferenceRegistryRequest request){
        PreferenceRegistry registry = repository.createUserPreferenceRegistry(convertCreatePreferenceRegistryRequest(request));
        return convertUserToInfoResponse(registry);
    }


    private PreferenceRegistryResponse convertUserToInfoResponse(PreferenceRegistry user) {
        PreferenceRegistryResponse response = new PreferenceRegistryResponse();
        response.setTenantId(user.getTenantId());
        response.setPreferenceType(user.getPreferenceType());
        response.setStatus(user.getStatus());
        response.setSchemaVersion(user.getSchemaVersion());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
    private PreferenceRegistry convertCreatePreferenceRegistryRequest(CreatePreferenceRegistryRequest request) {
        PreferenceRegistry user = new PreferenceRegistry();
        user.setTenantId(request.getTenantId());
        user.setPreferenceType(request.getPreferenceType());
        user.setStatus("ACTIVE");
        user.setSchemaVersion(request.getSchemaVersion());
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }

}
