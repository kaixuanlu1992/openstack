package com.wentt.openstack.controller.web;

import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.controller.vo.ImageVo;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.service.FlavorService;
import com.wentt.openstack.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "实例接口")
public class ServerController {

    @Autowired
    private FlavorService flavorService;
    @Autowired
    private ServerService serverService;
    @PostMapping("/server")
    @ApiOperation("创建实例")
    public String createServer(@RequestBody ServerDto serverDto){
        return serverService.createServer(serverDto);
    }

    @GetMapping("/server/list")
    @ApiOperation("获取实例列表")
    public List<ServerVo> getServerList(@RequestParam(name = "networkId",required = false)String networkId){
//        List<ServerVo> list=new ArrayList<>();
//        ServerVo server1=new ServerVo("22","server_ubuntu","10.0.7.11","ubuntu16.04","启动","无状态","2019-01-03","ww");
//        ServerVo server2=new ServerVo("22","server_centos7","10.0.6.11","centos7","启动","无状态","2019-01-03","ww");
//        ServerVo server3=new ServerVo("22","server_win7_1","10.0.8.12","windows7","启动","无状态","2019-01-03","ww");
//        ServerVo server4=new ServerVo("22","server_win7_2","10.0.8.13","windows7","启动","无状态","2019-01-03","ww");
//        ServerVo server5=new ServerVo("22","server_winxp","10.0.9.11","windows_xp","启动","无状态","2019-01-03","ww");
//        ServerVo server6=new ServerVo("22","server_winxp","10.0.9.12","windows_xp","启动","无状态","2019-01-03","ww");
//        list.add(server1);
//        list.add(server2);
//        list.add(server3);
//        list.add(server4);
//        list.add(server5);
//        list.add(server6);
//        return list;
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
        return false;
        //return serverService.deleteServer(serverId);
    }

    @GetMapping("/flavor/list")
    @ApiOperation("flavor列表")
    public List<FlavorVo> getFlavorList(){
        return flavorService.getFlavorList();
    }

    @GetMapping("/image/list")
    @ApiOperation("image列表")
    public List<ImageVo> getImageList(){
        return flavorService.getImageList();
    }

    @GetMapping("/server/url")
    @ApiOperation("主机控制台地址")
    public String getConsleUrl(String serverId){
        return serverService.getConsoleUrl(serverId);
    }
}
