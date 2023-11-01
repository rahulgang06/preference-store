package com.demo.preference_store.repository;

import com.demo.preference_store.entity.PreferenceRegistry;
import com.demo.preference_store.dao.PreferenceRegistryDao;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PreferenceRegistryRepository {
    Jdbi jdbi;

    public PreferenceRegistry createUserPreferenceRegistry(PreferenceRegistry preferenceRegistry) {
        return jdbi.withExtension(PreferenceRegistryDao.class, dao -> {
            Integer id = dao.insert(preferenceRegistry);
            preferenceRegistry.setId(Long.valueOf(id));
            return preferenceRegistry;
        });
    }
}
