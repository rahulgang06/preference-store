package com.demo.preference_store.controller;


import com.demo.preference_store.dto.CreatePreferencesRequest;
import com.demo.preference_store.dto.PreferencesResponse;
import com.demo.preference_store.service.PreferencesMgmtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PreferencesController {

    PreferencesMgmtService preferencesMgmtService;
    @PostMapping("/createPreferences")
    public PreferencesResponse createUser(@RequestBody CreatePreferencesRequest request){
        return preferencesMgmtService.createUser(request);
    }
    @PutMapping("/updatePreference")
    public PreferencesResponse update(@RequestBody CreatePreferencesRequest request){
        return preferencesMgmtService.updateUser(request);
    }
}
