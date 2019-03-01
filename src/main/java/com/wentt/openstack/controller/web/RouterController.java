package com.wentt.openstack.controller.web;

import com.wentt.openstack.controller.vo.RouterVo;
import com.wentt.openstack.service.RouterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "路由相关接口")
public class RouterController {
    @Autowired
    private RouterService routerService;
    @PostMapping("/router/")
    @ApiOperation("创建路由器")
    public String createRouter(@RequestBody RouterVo routerVo) {
        return routerService.createRouter(routerVo);
    }
}
