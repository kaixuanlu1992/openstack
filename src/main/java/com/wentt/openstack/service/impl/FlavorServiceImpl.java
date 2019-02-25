package com.wentt.openstack.service.impl;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.service.FlavorService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.OSClient.OSClientV2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlavorServiceImpl implements FlavorService {
    @Override
    public List<FlavorVo> getFlavorList() {
        List<FlavorVo> rs = new ArrayList<>();
        OSClientV2 os = CommonUitl.getAuthOs();
        os.compute().flavors().list().forEach(item -> {
            FlavorVo flavor=new FlavorVo();
            flavor.setId(item.getId());
            flavor.setName(item.getName());
        });
        return rs;
    }
}
