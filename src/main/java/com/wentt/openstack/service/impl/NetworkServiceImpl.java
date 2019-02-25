package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.service.NetworkService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.network.Network;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkServiceImpl implements NetworkService {
    @Override
    public List<NetworkVo> getNetworkList() {
        List<NetworkVo> rs = new ArrayList<>();
        OSClientV2 os = CommonUitl.getAuthOs();
        os.networking().network().list().forEach(item -> {
            NetworkVo temp = new NetworkVo();
            temp.setName(item.getName());
            temp.setId(item.getId());
            temp.setTenantName(os.identity().tenants().get(item.getTenantId()).getName());
            temp.setIsShared(item.isShared());
            temp.setType(item.getNetworkType().name());
            rs.add(temp);
        });
        return rs;
    }

    @Override
    public List<SubnetVo> getSubnetList(String networkId) {
        List<SubnetVo> rs = new ArrayList<>();
        OSClientV2 os = CommonUitl.getAuthOs();
        os.networking().network().get(networkId).getNeutronSubnets().forEach(item -> {
            SubnetVo subnet = new SubnetVo();
            subnet.setId(item.getId());
            subnet.setName(item.getName());
            subnet.setCidr(item.getCidr());
            subnet.setIpVersion(item.getIpVersion().name());
            subnet.setGetway(item.getGateway());
            subnet.setAddressPool(item.getAllocationPools().toString());
        });
        return rs;
    }

    @Override
    public String createNetwork(NetworkDto dto) {
        OSClientV2 os = CommonUitl.getAuthOs();
        Network network = CommonUitl.createNetwork(os, dto);
        return network.getId();
    }

    @Override
    public Boolean deleteNetwork(String networkId) {
        OSClientV2 os = CommonUitl.getAuthOs();
        return os.networking().network().delete(networkId).isSuccess();
    }
}
