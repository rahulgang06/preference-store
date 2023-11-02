package com.demo.preference_store.service;

import com.demo.preference_store.dto.PreferenceRegistryResponse;
import com.demo.preference_store.dto.PreferenceRegistryUpsertRequest;
import com.demo.preference_store.entity.PreferenceRegistry;
import com.demo.preference_store.repository.PreferenceRegistryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreferenceRegistryMgmtService {
    PreferenceRegistryRepository repository;

    public PreferenceRegistryResponse createPreferenceRegistry(PreferenceRegistryUpsertRequest request) {
        List<PreferenceRegistry> registries = repository.findPreferenceRegistryByPreferenceType(request.getTenantId(),
                request.getPreferenceType());

        if (!registries.isEmpty()) {
            throw new RuntimeException("already exists");
        }

        PreferenceRegistry registry = convertCreatePreferenceRegistryRequest(request, 1);
        PreferenceRegistry createdRegistry = repository.createPreferenceRegistry(registry);
        return convertPreferenceRegistryInfoResponse(createdRegistry);
    }

    public PreferenceRegistryResponse updatePreferenceRegistry(PreferenceRegistryUpsertRequest request) {
        List<PreferenceRegistry> registries = repository.findPreferenceRegistryByPreferenceType(request.getTenantId(),
                request.getPreferenceType());

        if (registries.isEmpty()) {
            throw new RuntimeException("registry does not exist");
        }

        Optional<PreferenceRegistry> latestPreferenceRegistry = registries.stream()
                .max(Comparator.comparingInt(PreferenceRegistry::getSchemaVersion));

        PreferenceRegistry registry = convertCreatePreferenceRegistryRequest(request, latestPreferenceRegistry.get()
                .getSchemaVersion() + 1);
        PreferenceRegistry createdRegistry = repository.createPreferenceRegistry(registry);
        return convertPreferenceRegistryInfoResponse(createdRegistry);
    }


    private PreferenceRegistryResponse convertPreferenceRegistryInfoResponse(PreferenceRegistry user) {
        PreferenceRegistryResponse response = new PreferenceRegistryResponse();
        response.setTenantId(user.getTenantId());
        response.setPreferenceType(user.getPreferenceType());
        response.setStatus(user.getStatus());
        response.setSchemaVersion(user.getSchemaVersion());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }

    private PreferenceRegistry convertCreatePreferenceRegistryRequest(PreferenceRegistryUpsertRequest request, int version) {
        PreferenceRegistry user = new PreferenceRegistry();
        user.setTenantId(request.getTenantId());
        user.setPreferenceType(request.getPreferenceType());
        user.setStatus("ACTIVE");
        user.setSchemaVersion(version);
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }

}
