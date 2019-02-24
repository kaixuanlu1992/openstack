package com.wentt.openstack.controller;

import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class NetworkController {
    @Value("${network.type}")
    private List<String> networkTypeList;

    @Autowired
    private NetworkService networkService;

    @GetMapping("/network/type/list")
    public List<String> getNetworkTypeList(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return networkTypeList;
    }

    @GetMapping("/network/list")
    public List<NetworkVo> getNetworkList(){
        return networkService.getNetworkList();
    }
    @GetMapping("/subnet/list")
    public List<SubnetVo> getSubnetList(@RequestParam("networkId")String networkId){
        return networkService.getSubnetList(networkId);
    }

    @PostMapping("/network")
    public String createNetwork(){
        return null;
    }

    @PutMapping("/network")
    public String updateNetwork(){
        return null;
    }

    @DeleteMapping("/network")
    public Boolean deleteNetwork(String networkId){
        return null;
    }

    @GetMapping("/server/list")
    public List<ServerVo> getServerList(){
        return null;
    }

    @PostMapping("/server")
    public String createServer(){
        return null;
    }

    @PutMapping("/server")
    public String updateServer(){
        return null;
    }

    @DeleteMapping("/server")
    public Boolean deleteServer(String serverId){
        return null;
    }
}
