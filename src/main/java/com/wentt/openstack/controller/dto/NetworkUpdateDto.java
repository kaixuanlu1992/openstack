package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class NetworkUpdateDto {
    private String name;
    private String id;
    private Boolean isShared;
    private Boolean stateUp;
}
