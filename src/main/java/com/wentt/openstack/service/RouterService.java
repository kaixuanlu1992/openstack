package com.wentt.openstack.service;

import com.wentt.openstack.controller.dto.PortAddDto;
import com.wentt.openstack.controller.vo.RouterVo;

import java.util.List;

public interface RouterService {
    /**
     * 获取路由列表
     * @return
     */
    List<RouterVo> getRouterList();

    /**
     * 创建路由器
     */
    String createRouter(RouterVo routerVo);

    String createPort(PortAddDto portAddDto);
    List<PortVo> getPortList();
    String deletePort(String portId);
}
