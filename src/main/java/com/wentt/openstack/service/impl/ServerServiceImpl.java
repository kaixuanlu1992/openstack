package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.service.ServerService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements ServerService {
    @Override
    public List<ServerVo> getServerList(String networkId) {
        List<ServerVo> rs = new ArrayList<>();
        OSClientV2 os = CommonUitl.getAuthOs();
        os.compute().servers().list().forEach(item -> {
            ServerVo server = new ServerVo();
            server.setId(item.getId());
            server.setName(item.getName());
            server.setIpAddress(item.getAccessIPv4());
            server.setFlavor(item.getFlavor().getName());
            server.setImage(item.getImage().getName());
        });
        return rs;
    }

    @Override
    public String createServer(ServerDto server) {
        OSClientV2 os = CommonUitl.getAuthOs();
        ServerCreate sc = Builders.server()
                .name(server.getName())
                .flavor(server.getFlavorId())
                .image(server.getImageId())
                .addSecurityGroup("default")
                .networks(server.getNetworkId())
                .build();
        return os.compute().servers().boot(sc).getId();
    }

    @Override
    public Boolean deleteServer(String serverId) {
        OSClientV2 os = CommonUitl.getAuthOs();
        return os.compute().servers().delete(serverId).isSuccess();
    }
}
