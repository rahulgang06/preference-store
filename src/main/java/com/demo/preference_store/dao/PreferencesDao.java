package com.demo.preference_store.dao;

import com.demo.preference_store.entity.Preference;
import com.demo.preference_store.entity.PreferenceRegistry;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PreferencesDao {
    @SqlUpdate("INSERT INTO `preferences` (`tenant_id`, `preference_type`, `resource_id`, `schema_version`, `owner`, `status`, `created_at`, `updated_at`) "
            + "VALUES (:u.tenantId, :u.preferenceType, :u.resourceID, :u.schemaVersion, :u.owner, :u.status, :u.createdAt, :u.updatedAt)")
    @GetGeneratedKeys
    Integer insert(@BindBean("u") Preference preference);



    // ToDo: update with primary index `id`
    @SqlUpdate("UPDATE `preferences` SET `schema_version` = :schemaVersion WHERE `tenant_id` = :u.tenantId AND `owner`= :u.owner AND `preference_type` = :u.preferenceType")
    void updateEntity(@BindBean ("u") Preference preference,@Bind("schemaVersion") int schemaVersion);


    @SqlQuery("SELECT `tenant_id`, `preference_type`, `schema_version`, `status`, `created_at`, `updated_at` FROM " +
            "`preferences` WHERE `tenant_id` = :tenantId AND `preference_type` = :preferenceType AND `owner` = :owner AND `resource_id` = :resourceId")

    List<Preference> findAllBy(@Bind("tenantId") String tenantId,
                               @Bind("owner") String owner,
                               @Bind("preferenceType") String preferenceType,
                               @Bind("resourceId") String resourceId);

}
