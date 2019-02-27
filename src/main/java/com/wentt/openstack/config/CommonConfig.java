package com.wentt.openstack.config;

import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.OSClient.OSClientV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Bean
    public OSClientV2 osClientV2(){
        return CommonUitl.getAuthOs();
    }
}
