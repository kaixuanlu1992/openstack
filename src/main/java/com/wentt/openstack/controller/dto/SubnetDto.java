package com.wentt.openstack.controller.dto;

import lombok.Data;

@Data
public class SubnetDto {
    private String name;
    private String networkId;
    private String cidr;
    private String ipVersion;
    private String gateway;
    private String addressPoolStart;
    private String addressPoolEnd;
}
