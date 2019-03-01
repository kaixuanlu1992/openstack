package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String tenantId;
    private Boolean enable;
    private String password;

}
