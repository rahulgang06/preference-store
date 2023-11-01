package com.demo.preference_store.controller;

import com.demo.preference_store.dto.CreatePreferenceRegistryRequest;
import com.demo.preference_store.dto.PreferenceRegistryResponse;
import com.demo.preference_store.entity.PreferenceRegistry;
import com.demo.preference_store.service.PreferenceRegistryMgmtService;
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

public class PreferenceRegistryController {

    PreferenceRegistryMgmtService preferenceRegistryMgmtService;
    @PostMapping("/createPreferenceRegistry")
    public PreferenceRegistryResponse createUser(@RequestBody CreatePreferenceRegistryRequest request){
        return preferenceRegistryMgmtService.createUser(request);
    }

    @PutMapping("/updatePreferenceRegistry")
    public PreferenceRegistryResponse updateUser(@RequestBody CreatePreferenceRegistryRequest request){
        return preferenceRegistryMgmtService.createUser(request);
    }
}
