package com.wentt.openstack.controller;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.service.FlavorService;
import com.wentt.openstack.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "实例接口")
public class ServerController {

    @Autowired
    private FlavorService flavorService;
    @Autowired
    private ServerService serverService;
    @PostMapping("/server/list")
    @ApiOperation("获取实例列表")
    public String createServer(@RequestBody ServerDto serverDto){
        return serverService.createServer(serverDto);
    }
    @PostMapping("/server")
    @ApiOperation("创建实例")
    public List<ServerVo> getServerList(@RequestParam("networkId")String networkId){
        return serverService.getServerList(networkId);
    }

    @PutMapping("/server")
    @ApiOperation("更新实例")
    public String updateServer(){
        return null;
    }

    @DeleteMapping("/server")
    @ApiOperation("删除实例")
    public Boolean deleteServer(String serverId){
        return serverService.deleteServer(serverId);
    }

    @GetMapping("/flavor/list")
    @ApiOperation("flavor列表")
    public List<FlavorVo> getFlavorList(){
        return flavorService.getFlavorList();
    }
}
