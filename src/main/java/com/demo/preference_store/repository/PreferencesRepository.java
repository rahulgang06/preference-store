package com.demo.preference_store.repository;


import com.demo.preference_store.dao.PreferencesDao;
import com.demo.preference_store.entity.Preference;
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

    public Preference createPreference(Preference preference) {
        return jdbi.withExtension(PreferencesDao.class, dao -> {
            Integer id = dao.insert(preference);
            preference.setId(Long.valueOf(id));
            return preference;
        });
    }


    public Preference updatePreference(Preference preference) {
        return jdbi.withExtension(PreferencesDao.class, dao -> {
            dao.updateEntity(preference);
            return preference;
        });
    }
}
