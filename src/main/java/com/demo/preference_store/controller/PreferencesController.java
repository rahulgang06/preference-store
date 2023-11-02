package com.demo.preference_store.controller;


import com.demo.preference_store.dto.CreatePreferenceRequest;
import com.demo.preference_store.dto.PreferenceResponse;
import com.demo.preference_store.service.PreferencesMgmtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/preference")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreferencesController {

    PreferencesMgmtService preferencesMgmtService;

    @PostMapping("/")
    public PreferenceResponse createPreference(@RequestBody CreatePreferenceRequest request) {
        return preferencesMgmtService.createPreference(request);
    }

    @PutMapping("/")
    public PreferenceResponse update(@RequestBody CreatePreferenceRequest request) {
        return preferencesMgmtService.updatePreference(request);
    }
}
