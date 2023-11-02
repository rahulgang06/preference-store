package com.demo.preference_store.dao;

import com.demo.preference_store.entity.PreferenceRegistry;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PreferenceRegistryDao {
    @SqlUpdate("INSERT INTO `preference_registry` (`tenant_id`, `preference_type`, `schema_version`, `status`, " +
            "`created_at`, `updated_at`) VALUES " +
            "(:u.tenantId, :u.preferenceType, :u.schemaVersion, :u.status, :u.createdAt, :u.updatedAt)")
    @GetGeneratedKeys
    Integer insert(@BindBean("u") PreferenceRegistry preferenceRegistry);

    @SqlQuery("SELECT `tenant_id`, `preference_type`, `schema_version`, `status`, `created_at`, `updated_at` FROM " +
            "`preference_registry` WHERE `tenant_id` = :tenantId AND `preference_type` = :preferenceType")
    List<PreferenceRegistry> findAllByTenantIdAndPreferenceType(@Bind("tenantId") String tenantId,
                                                                @Bind("preferenceType") String preferenceType);
}

