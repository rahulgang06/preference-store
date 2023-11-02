package com.demo.preference_store.service;

import com.demo.preference_store.dto.CreatePreferenceRequest;
import com.demo.preference_store.dto.PreferenceResponse;
import com.demo.preference_store.entity.Preference;
import com.demo.preference_store.repository.PreferenceRegistryRepository;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreferencesMgmtService {

    PreferenceRegistryRepository preferenceRegistryRepository;

    PreferencesRepository repository;

    public PreferenceResponse createPreference(CreatePreferenceRequest request) {
        // ToDo check if already exist, then throw error
        // ToDo: validate in preference registry, if preference type exists.

        // ToDo get max of preference regsitry.

        // Pass latest schema version to create preference.
        Preference preference = repository.createPreference(convertCreatePreferencesRequest(request, 0));
        return convertPreferencesToInfoResponse(preference);
    }

    // ToDo: create separate request object for updatePreference (schemaVersion is extra)
    public PreferenceResponse updatePreference(CreatePreferenceRequest request) {
        // ToDo check if preferenceRegistry with specific schema version exists or not.
        // ToDo get and check if preference exists or not (if not throw exception)
        // ToDo Schema Version will come from user.
        Preference preference = repository.updatePreference(convertCreatePreferencesRequest(request, 0));
        return convertPreferencesToInfoResponse(preference);
    }


    private PreferenceResponse convertPreferencesToInfoResponse(Preference user) {
        PreferenceResponse response = new PreferenceResponse();
        response.setTenantId(user.getTenantId());
        response.setPreferenceType(user.getPreferenceType());
        response.setResourceID(user.getResourceId());
        response.setOwner(user.getOwner());
        response.setSchemaVersion(user.getSchemaVersion());
        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }

    private Preference convertCreatePreferencesRequest(CreatePreferenceRequest request, int schemaVersion) {
        Preference user = new Preference();
        user.setTenantId(request.getTenantId());
        user.setPreferenceType(request.getPreferenceType());
        user.setStatus("ACTIVE");
        user.setOwner(request.getOwner());
        user.setResourceId(UUID.randomUUID()
                .toString());
        user.setSchemaVersion(schemaVersion);
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }
}
