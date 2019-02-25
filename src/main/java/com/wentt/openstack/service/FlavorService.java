package com.wentt.openstack.service;

import com.wentt.openstack.controller.vo.FlavorVo;

import java.util.List;

public interface FlavorService {
    List<FlavorVo> getFlavorList();
}
