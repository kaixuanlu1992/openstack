package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class NetworkServerCountDto {
    private String networkId;
    private Integer count;
}
