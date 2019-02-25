package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class NetworkDto {
    private String name;
    private String tenantId;
    private String physicalNetwork;
    private String type;
    private String segmentId;
    private Boolean isShared;
    private Boolean stateUp;
}
