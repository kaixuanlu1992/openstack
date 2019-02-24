package com.wentt.openstack.controller.vo;

import lombok.Data;

@Data
public class ServerVo {
    private String id;
    private String name;
    private String ipAddress;
    private String image;
    private String flavor;
}
