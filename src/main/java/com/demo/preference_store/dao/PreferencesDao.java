package com.demo.preference_store.dao;

import com.demo.preference_store.entity.Preferences;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface PreferencesDao {
    @SqlUpdate("INSERT INTO `preferences` (`tenant_id`, `preference_type`, `resource_id`, `schema_version`, `owner`, `status`, `created_at`, `updated_at`) "
            + "VALUES (:u.tenantId, :u.preferenceType, :u.resourceID, :u.schemaVersion, :u.owner, :u.status, :u.createdAt, :u.updatedAt)")
    @GetGeneratedKeys
    Integer insert(@BindBean("u") Preferences preferences);



    @SqlUpdate("UPDATE `preferences` SET `schema_version` = :u.schemaVersion WHERE `tenant_id` = :u.tenantId AND `owner`= :u.owner AND `preference_type` = :u.preferenceType")
    Integer updateEntity(@BindBean ("u") Preferences preferences);

}
