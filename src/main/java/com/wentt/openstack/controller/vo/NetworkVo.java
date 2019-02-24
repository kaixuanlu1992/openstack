package com.wentt.openstack.controller.vo;

import lombok.Data;

@Data
public class NetworkVo {
    private String id;
    private String name;
    private String tenantId;
    private String tenantName;
    private String type;
    private String subnet;
    private Boolean isShared;
}
