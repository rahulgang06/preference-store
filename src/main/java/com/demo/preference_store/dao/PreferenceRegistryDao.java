package com.demo.preference_store.dao;

import com.demo.preference_store.entity.PreferenceRegistry;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface PreferenceRegistryDao {
    @SqlUpdate("INSERT INTO `preferenceregistry` (`tenant_id`, `preference_type`, `schema_version`, `status`, `created_at`, `updated_at`) "
            + "VALUES (:u.tenantId, :u.preferenceType, :u.schemaVersion, :u.status, :u.createdAt, :u.updatedAt)")
    @GetGeneratedKeys
    Integer insert(@BindBean("u") PreferenceRegistry preferenceRegistry);

}

