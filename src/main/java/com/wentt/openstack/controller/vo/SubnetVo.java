package com.wentt.openstack.controller.vo;

import lombok.Data;

@Data
public class SubnetVo {
    private String id;
    private String name;
    private String cidr;
    private String ipVersion;
    private String gateway;
    private String addressPoolStart;
    private String addressPoolEnd;
}
