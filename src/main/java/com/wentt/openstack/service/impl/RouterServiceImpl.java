package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.PortAddDto;
import com.wentt.openstack.controller.vo.RouterVo;
import com.wentt.openstack.service.PortVo;
import com.wentt.openstack.service.RouterService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.network.AttachInterfaceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouterServiceImpl implements RouterService {
    /**
     * 获取路由器列表
     *
     * @return
     */
    @Override
    public List<RouterVo> getRouterList() {
        OSClientV2 os = CommonUitl.getAuthOs();
        List<RouterVo> rs = new ArrayList<>();
        os.networking().router().list().forEach(item -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setId(item.getId());
            routerVo.setName(item.getName());
            routerVo.setIsAdminUp(item.isAdminStateUp());
        });
        return rs;
    }

    @Override
    public String createRouter(RouterVo routerVo) {
        OSClientV2 os = CommonUitl.getAuthOs();
        return os.networking().router().create(Builders.router()
                .adminStateUp(routerVo.getIsAdminUp())
                .name(routerVo.getName())
                .build()).getId();
    }

    @Override
    public String createPort(PortAddDto portAddDto) {
        return null;
    }

    @Override
    public List<PortVo> getPortList() {
        return null;
    }

    @Override
    public String deletePort(String portId) {
        return null;
    }

    /**
     * 连接子网到路由器
     *
     * @param routerId
     * @param subnetId
     */
    public void concatSubnetToRouter(String routerId, String subnetId) {
        OSClientV2 os = CommonUitl.getAuthOs();
        os.networking().router().attachInterface(routerId, AttachInterfaceType.SUBNET, subnetId);
    }
}
