package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class NetworkDto {
    private String name;
    private String tenantId;
    private String type;
    private Boolean isShared;
}
