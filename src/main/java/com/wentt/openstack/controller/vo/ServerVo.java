package com.wentt.openstack.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerVo {
    private String id;
    private String name;
    private String ipAddress;
    private String imageName;
    private String state;
    private String powerState;
    private String createTime;
    private String consoleUrl;
}
