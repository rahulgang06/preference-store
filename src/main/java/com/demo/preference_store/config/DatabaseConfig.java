package com.demo.preference_store.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatabaseConfig {
    String user;

    String password;

    String url;

    int acquireIncrement;

    int initialPoolSize;

    int minPoolSize;

    int maxPoolSize;

    int maxIdleTime;

    int maxStatements;

    int idleConnectionTestPeriod;

    String testStatement;

    String poolName;

    int connectionTimeout;

    int maxLifeTime;

    boolean autoCommit;

    boolean readOnly;

    int keepAliveTime;
}
