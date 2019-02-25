package com.wentt.openstack.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServerDto {
    private String name;
    private List<String> networkId;
    private String flavorId;
    private String imageId;
}
