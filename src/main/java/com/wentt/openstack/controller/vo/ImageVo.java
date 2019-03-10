package com.wentt.openstack.controller.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ImageVo {
    private String id;
    private String name;
    private String state;
    private String type;
    private String size;
    private Date createTime;
    private Boolean isProtected;
    private String diskFormat;
    private Boolean isPublic;

}
