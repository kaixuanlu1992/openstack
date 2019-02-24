package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.service.NetworkService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.network.Subnet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkServiceImpl implements NetworkService {
    @Override
    public List<NetworkVo> getNetworkList() {
        List<NetworkVo> rs = new ArrayList<>();
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        os.networking().network().list().forEach(item -> {
            NetworkVo temp = new NetworkVo();
            temp.setName(item.getName());
            temp.setId(item.getId());
            temp.setTenantId(item.getTenantId());

            rs.add(temp);
        });
        return rs;
    }
}
