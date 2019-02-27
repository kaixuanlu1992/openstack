package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.dao.TNetworkServerMapMapper;
import com.wentt.openstack.model.TNetworkServerMap;
import com.wentt.openstack.service.ServerService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private TNetworkServerMapMapper tNetworkServerMapMapper;
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
    public String createServer(ServerDto serverDto) {
        OSClientV2 os = CommonUitl.getAuthOs();
        ServerCreate sc = Builders.server()
                .name(serverDto.getName())
                .flavor(serverDto.getFlavorId())
                .image(serverDto.getImageId())
                .addSecurityGroup("default")
                .networks(serverDto.getNetworkId())
                .build();
        Server server=os.compute().servers().boot(sc);

        TNetworkServerMap record=new TNetworkServerMap();
        serverDto.getNetworkId().forEach(network->{
            record.setId(UUID.randomUUID().toString().replace("-",""));
            record.setNetworkId(network);
            record.setServerId(server.getId());
            tNetworkServerMapMapper.insert(record);
        });
        return server.getId();
    }

    @Override
    public Boolean deleteServer(String serverId) {
        OSClientV2 os = CommonUitl.getAuthOs();
        return os.compute().servers().delete(serverId).isSuccess();
    }
}
