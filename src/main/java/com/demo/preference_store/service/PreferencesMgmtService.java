package com.demo.preference_store.service;

import com.demo.preference_store.dto.CreatePreferenceRequest;
import com.demo.preference_store.dto.PreferenceResponse;
import com.demo.preference_store.entity.Preference;
import com.demo.preference_store.entity.PreferenceRegistry;
import com.demo.preference_store.repository.PreferenceRegistryRepository;
import com.demo.preference_store.repository.PreferencesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreferencesMgmtService {

    PreferenceRegistryRepository preferenceRegistryRepository;

    PreferencesRepository repository;

    public PreferenceResponse createPreference(CreatePreferenceRequest request) {
        // ToDo check if already exist, then throw error

        List<Preference>preferenceList = repository.findPreferenceBy(request.getTenantId(),request.getOwner(),request.getPreferenceType(),request.getResourceId());

        if(!preferenceList.isEmpty()){
            throw new RuntimeException("already exists");
        }

        // ToDo: validate in preference registry, if preference type exists.
        List<PreferenceRegistry>registries = preferenceRegistryRepository.findPreferenceRegistryByPreferenceType(request.getTenantId(), request.getPreferenceType());
        if(!registries.isEmpty()){
            throw new RuntimeException("already exists");
        }

        // ToDo get max of preference regsitry.
        Optional<PreferenceRegistry> latestPreferenceRegistry = registries.stream()
                .max(Comparator.comparingInt(PreferenceRegistry::getSchemaVersion));

             int x = latestPreferenceRegistry.get().getSchemaVersion();


        // Pass latest schema version to create preference.
        Preference preference = repository.createPreference(convertCreatePreferencesRequest(request, x));
        return convertPreferencesToInfoResponse(preference);
    }

    // ToDo: create separate request object for updatePreference (schemaVersion is extra)

    public PreferenceResponse updatePreference(CreatePreferenceRequest request,int schemaVersion) {
        // ToDo check if preferenceRegistry with specific schema version exists or not.
        List<PreferenceRegistry>registries = preferenceRegistryRepository.findPreferenceRegistryByPreferenceType(request.getTenantId(), request.getPreferenceType());
        boolean isValuePresent = registries.stream()
                .anyMatch(obj -> obj.getSchemaVersion()==schemaVersion);
        if(isValuePresent){
            throw new RuntimeException("already exists");
        }



        // ToDo get and check if preference exists or not (if not throw exception)
        List<Preference>preferenceList = repository.findPreferenceBy(request.getTenantId(),request.getOwner(),request.getPreferenceType(),request.getResourceId());
        if(!preferenceList.isEmpty()){
            throw new RuntimeException("already exists");
        }

        // ToDo Schema Version will come from user.
        Preference preference = repository.updatePreference(convertCreatePreferencesRequest(request, schemaVersion),schemaVersion);
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
        user.setResourceId(request.getResourceId());
        user.setSchemaVersion(schemaVersion);
        user.setDescription(request.getDescription());
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }
}
