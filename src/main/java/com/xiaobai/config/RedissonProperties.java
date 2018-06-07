package com.xiaobai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson 配置属性
 * 为了直接使用redis锁
 *
 * @author bail
 * @date 2018/6/7.12:24
 */
@ConfigurationProperties(prefix = "redisson")
@Data
public class RedissonProperties {

    private String address;

    private int database;

    private String password;

    private int timeout;

    private int connectionPoolSize;

    private int connectionMinimumIdleSize;

    //以上为单机模式
    //以下为哨兵模式

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;
}