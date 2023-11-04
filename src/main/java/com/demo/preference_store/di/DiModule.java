package com.demo.preference_store.di;
import com.demo.preference_store.config.AppConfig;
import com.demo.preference_store.config.DatabaseConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.common.util.StringUtils;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiModule {

    @Bean
    public Jdbi provideJdbi(HikariDataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
//        jdbi.setSqlLogger(new Slf4jSqlLogger());
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public HikariDataSource hikariDataSource(final AppConfig appConfig) {
        DatabaseConfig databaseConfig = appConfig.getDbConfig();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseConfig.getUrl());
        hikariConfig.setUsername(databaseConfig.getUser());
        hikariConfig.setPassword(databaseConfig.getPassword());
        hikariConfig.setMinimumIdle(databaseConfig.getMinPoolSize());
        hikariConfig.setMaximumPoolSize(databaseConfig.getMaxPoolSize());
        hikariConfig.setIdleTimeout(databaseConfig.getMaxIdleTime());
        hikariConfig.setMaxLifetime(databaseConfig.getMaxIdleTime());
        hikariConfig.setAutoCommit(databaseConfig.isAutoCommit());
        hikariConfig.setReadOnly(databaseConfig.isReadOnly());
        hikariConfig.setConnectionInitSql(databaseConfig.getTestStatement());
        hikariConfig.setConnectionTimeout(databaseConfig.getConnectionTimeout());
        hikariConfig.setKeepaliveTime(databaseConfig.getKeepAliveTime());

        String poolName =
                StringUtils.isBlank(databaseConfig.getPoolName())
                        ? "DEFAULT_DB_POOL_NAME"
                        : databaseConfig.getPoolName();
        hikariConfig.setPoolName(poolName);

        return new HikariDataSource(hikariConfig);
    }
}
