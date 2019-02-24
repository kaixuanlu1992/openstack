package com.wentt.openstack.service;

import com.wentt.openstack.controller.vo.NetworkVo;

import java.util.List;

public interface NetworkService {
    List<NetworkVo> getNetworkList();
}
