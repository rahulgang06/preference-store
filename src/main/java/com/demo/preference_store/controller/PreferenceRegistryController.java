package com.demo.preference_store.controller;

import com.demo.preference_store.dto.PreferenceRegistryUpsertRequest;
import com.demo.preference_store.dto.PreferenceRegistryResponse;
import com.demo.preference_store.service.PreferenceRegistryMgmtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/api/v1/preference/registry")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PreferenceRegistryController {

    PreferenceRegistryMgmtService preferenceRegistryMgmtService;
    @PostMapping("/")
    public PreferenceRegistryResponse createPreferenceRegistry(@RequestBody PreferenceRegistryUpsertRequest request){
        return preferenceRegistryMgmtService.createPreferenceRegistry(request);
    }

    @PutMapping("/")
    public PreferenceRegistryResponse updatePreferenceRegistry(@RequestBody PreferenceRegistryUpsertRequest request){
        return preferenceRegistryMgmtService.updatePreferenceRegistry(request);
    }
}
