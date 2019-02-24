package com.wentt.openstack.service;

import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.SubnetVo;

import java.util.List;

public interface NetworkService {
    /**
     * 获取网络信息
     * @return
     */
    List<NetworkVo> getNetworkList();

    /**
     * 获取子网信息
     * @param networkId
     * @return
     */
    List<SubnetVo> getSubnetList(String networkId);
}
