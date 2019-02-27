package com.wentt.openstack.controller.vo;

import lombok.Data;
import org.openstack4j.model.network.State;

@Data
public class NetworkVo {
    private String id;
    private String name;
    private String tenantId;
    private String tenantName;
    private String type;
    private Boolean isShared;
    private Boolean isAdminStateUp;
    private State state;
    private Boolean isShare;
    private Integer subnetNumber;
    private Integer serverNumber;
}
