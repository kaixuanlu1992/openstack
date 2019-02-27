package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.dto.NetworkServerCountDto;
import com.wentt.openstack.controller.dto.NetworkUpdateDto;
import com.wentt.openstack.controller.dto.SubnetDto;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.RouterVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.dao.TNetworkServerMapMapper;
import com.wentt.openstack.service.NetworkService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.network.AttachInterfaceType;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NetworkServiceImpl implements NetworkService {
    @Autowired
    private OSClientV2 os;
    @Autowired
    private TNetworkServerMapMapper tNetworkServerMapMapper;

    @Override
    public List<NetworkVo> getNetworkList() {
        List<NetworkVo> rs = new ArrayList<>();
        OSClientV2 os = CommonUitl.getAuthOs();
        List<NetworkServerCountDto> networkServerCountList = tNetworkServerMapMapper.getNetworkServerCount();
        Map<String, Integer> networkCountMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(networkServerCountList)) {
            networkCountMap = networkServerCountList.stream()
                    .collect(Collectors.toMap(NetworkServerCountDto::getNetworkId, NetworkServerCountDto::getCount));
        }
        Map<String, Integer> finalNetworkCountMap = networkCountMap;
        os.networking().network().list().forEach(item -> {
            NetworkVo temp = new NetworkVo();
            temp.setName(item.getName());
            temp.setId(item.getId());
            temp.setTenantId(item.getTenantId());
            temp.setTenantName(os.identity().tenants().get(item.getTenantId()).getName());
            temp.setIsShared(item.isShared());
            temp.setType(item.getNetworkType().name());
            temp.setState(item.getStatus());
            temp.setSubnetNumber(item.getNeutronSubnets() == null ? 0 : item.getNeutronSubnets().size());
            temp.setIsAdminStateUp(item.isAdminStateUp());
            temp.setServerNumber(finalNetworkCountMap.get(item.getId()) == null ? 0 : finalNetworkCountMap.get(item.getId()));
            rs.add(temp);
        });
        return rs;
    }

    @Override
    public List<SubnetVo> getSubnetList(String networkId) {
        List<SubnetVo> rs = new ArrayList<>();
        os.networking().network().get(networkId).getNeutronSubnets().forEach(item -> {
            SubnetVo subnet = new SubnetVo();
            subnet.setId(item.getId());
            subnet.setName(item.getName());
            subnet.setCidr(item.getCidr());
            subnet.setIpVersion(item.getIpVersion().name());
            subnet.setGateway(item.getGateway());
            subnet.setAddressPool(item.getAllocationPools().toString());
        });
        return rs;
    }

    @Override
    public String createNetwork(NetworkDto dto) {
        Network network = CommonUitl.createNetwork(os, dto);
        return network.getId();
    }

    @Override
    public Boolean deleteNetwork(String networkId) {
        return os.networking().network().delete(networkId).isSuccess();
    }

    @Override
    public void deleteSubnet(List<String> subnetIdList) {
        subnetIdList.forEach(id -> {
            os.networking().subnet().delete(id);
        });
    }

    @Override
    public String createSubnet(SubnetDto subnet) {
        return os.networking().subnet().create(Builders.subnet()
                .name(subnet.getName())
                .networkId(subnet.getNetworkId())
                .tenantId(subnet.getTenantId())
                .addPool(subnet.getAddressPoolStart(), subnet.getAddressPoolEnd())
                .ipVersion(IPVersionType.V4)
                .cidr(subnet.getCidr())
                .build()).getId();
    }

    @Override
    public void updateNetwork(NetworkUpdateDto dto) {
        os.networking().network().update(dto.getId(),
                Builders.networkUpdate()
                        .adminStateUp(dto.getStateUp())
                        .name(dto.getName())
                        .shared(dto.getIsShared())
                        .build());
    }

    @Override
    public String createRouter(RouterVo routerVo) {
        return os.networking().router().create(Builders.router()
                .adminStateUp(routerVo.getIsAdminUp())
                .name(routerVo.getName())
                .build()).getId();
    }

    /**
     * 连接子网到路由器
     *
     * @param routerId
     * @param subnetId
     */
    public void concatSubnetToRouter(String routerId, String subnetId) {
        os.networking().router().attachInterface(routerId, AttachInterfaceType.SUBNET, subnetId);
    }

    /**
     * 获取路由器列表
     *
     * @return
     */
    public List<RouterVo> getRouterList() {
        List<RouterVo> rs = new ArrayList<>();
        os.networking().router().list().forEach(item -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setId(item.getId());
            routerVo.setName(item.getName());
            routerVo.setIsAdminUp(item.isAdminStateUp());
        });
        return rs;
    }

}
