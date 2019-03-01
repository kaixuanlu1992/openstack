package com.wentt.openstack.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ServerVo {
    private String id;
    private String name;
    private String ipAddress;
    private String imageName;
    private String state;
    private String powerState;
    private Date createTime;
}
