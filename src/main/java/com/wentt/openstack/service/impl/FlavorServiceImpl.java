package com.wentt.openstack.service.impl;


import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.controller.vo.ImageVo;
import com.wentt.openstack.service.FlavorService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public List<ImageVo> getImageList() {
        OSClientV2 os = CommonUitl.getAuthOs();
        List<? extends Image> images =os.images().list();
        List<ImageVo> rs=new ArrayList<>();
        if (!CollectionUtils.isEmpty(images)){
            images.forEach(item->{
                ImageVo image=new ImageVo();
                image.setId(image.getId());
                image.setName(image.getName());
                rs.add(image);
            });
        }
        return rs;
    }
}
