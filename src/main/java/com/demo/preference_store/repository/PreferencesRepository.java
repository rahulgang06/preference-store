package com.demo.preference_store.repository;


import com.demo.preference_store.dao.PreferencesDao;
import com.demo.preference_store.entity.Preferences;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PreferencesRepository {

    Jdbi jdbi;

    public Preferences createUserPreferences(Preferences preferences) {
        return jdbi.withExtension(PreferencesDao.class, dao -> {
            Integer id = dao.insert(preferences);
            preferences.setId(Long.valueOf(id));
            return preferences;
        });
    }


    public Preferences updateUserPreferences(Preferences preferences) {
        return jdbi.withExtension(PreferencesDao.class, dao -> {
            Integer id = dao.updateEntity(preferences);
            preferences.setId(Long.valueOf(id));
            return preferences;
        });
    }
}
