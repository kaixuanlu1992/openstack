package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.dao.TNetworkServerMapMapper;
import com.wentt.openstack.model.TNetworkServerMap;
import com.wentt.openstack.model.TNetworkServerMapExample;
import com.wentt.openstack.service.ServerService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private OSClientV2 os;
    @Autowired
    private TNetworkServerMapMapper tNetworkServerMapMapper;

    @Override
    public List<ServerVo> getServerList(String networkId) {
        List<ServerVo> rs = new ArrayList<>();
        TNetworkServerMapExample example = new TNetworkServerMapExample();
        example.createCriteria().andNetworkIdEqualTo(networkId);
        List<TNetworkServerMap> tNetworkServerList = tNetworkServerMapMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tNetworkServerList)) {
            return new ArrayList<>();
        }

        List<String> serverIdList = tNetworkServerList.stream().map(TNetworkServerMap::getServerId).collect(Collectors.toList());

        serverIdList.forEach(item -> {
            Server server = os.compute().servers().get(item);
            ServerVo serverVo = new ServerVo();
            serverVo.setId(server.getId());
            serverVo.setName(server.getName());
            serverVo.setIpAddress(server.getAccessIPv4());
            serverVo.setFlavor(server.getFlavor().getName());
            serverVo.setImage(server.getImage().getName());
            rs.add(serverVo);
        });
        return rs;
    }

    @Override
    public String createServer(ServerDto serverDto) {
        ServerCreate sc = Builders.server()
                .name(serverDto.getName())
                .flavor(serverDto.getFlavorId())
                .image(serverDto.getImageId())
                .addSecurityGroup("default")
                .networks(serverDto.getNetworkId())
                .build();
        Server server = os.compute().servers().boot(sc);

        TNetworkServerMap record = new TNetworkServerMap();
        serverDto.getNetworkId().forEach(network -> {
            record.setId(UUID.randomUUID().toString().replace("-", ""));
            record.setNetworkId(network);
            record.setServerId(server.getId());
            tNetworkServerMapMapper.insert(record);
        });
        return server.getId();
    }

    @Override
    public Boolean deleteServer(String serverId) {
        return os.compute().servers().delete(serverId).isSuccess();
    }

    @Override
    public String getConsoleUrl(String serverId) {
        OSClientV2 os= CommonUitl.getAuthOs();
        return os.compute().servers().getVNCConsole(serverId,null).getURL();
    }
}
