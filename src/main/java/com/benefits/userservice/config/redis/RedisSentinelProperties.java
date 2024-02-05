package com.benefits.userservice.config.redis;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisSentinelProperties {

    @Value("${spring.redis.master.name}")
    private String masterName;

    @Value("${spring.redis.slave.ip}")
    private String slaveIp;

    @Value("${spring.redis.slave.sentinel-port}")
    private Integer slaveSentinelPort;

    @Value("${spring.redis.sentinel.ip}")
    private String sentinelIp;

    @Value("${spring.redis.sentinel.sentinel-port}")
    private Integer sentinelPort;
}
