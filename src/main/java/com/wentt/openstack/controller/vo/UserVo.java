package com.wentt.openstack.controller.vo;

import lombok.Data;

@Data
public class UserVo {
    private String id;
    private String name;
    private String email;
    private String tenantName;
    private Boolean state;
    private String password;
}
