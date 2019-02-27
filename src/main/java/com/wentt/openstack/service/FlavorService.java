package com.wentt.openstack.service;

import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.controller.vo.ImageVo;

import java.util.List;

public interface FlavorService {
    /**
     * 获取flavor列表
     * @return
     */
    List<FlavorVo> getFlavorList();

    /**
     * 获取image列表
     * @return
     */
    List<ImageVo> getImageList();
}
