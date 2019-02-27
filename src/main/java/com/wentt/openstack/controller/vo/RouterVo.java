package com.wentt.openstack.controller.vo;

import lombok.Data;

@Data
public class RouterVo {
    private String id;
    private String name;
    private Boolean isAdminUp;
}
