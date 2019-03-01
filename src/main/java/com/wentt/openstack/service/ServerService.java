package com.wentt.openstack.service;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.ServerVo;

import java.util.List;

public interface ServerService {
    /**
     * 获取实例列表
     * @param networkId
     * @return
     */
    List<ServerVo> getServerList(String networkId);

    /**
     * 创建实例
     * @param serverDto
     * @return
     */
    String createServer(ServerDto serverDto);

    /**
     * 删除实例
     * @param serverId
     * @return
     */
    Boolean deleteServer(String serverId);

    /**
     * 获取主机控制台地址
     * @param serverId
     * @return
     */
    String getConsoleUrl(String serverId);
}
