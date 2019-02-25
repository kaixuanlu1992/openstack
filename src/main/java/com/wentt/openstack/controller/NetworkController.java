package com.wentt.openstack.controller;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.dto.ServerDto;
import com.wentt.openstack.controller.dto.SubnetDto;
import com.wentt.openstack.controller.vo.FlavorVo;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.ServerVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.service.FlavorService;
import com.wentt.openstack.service.NetworkService;
import com.wentt.openstack.service.ServerService;
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
    @Autowired
    private FlavorService flavorService;
    @Autowired
    private ServerService serverService;


    @GetMapping("/network/type/list")
    public List<String> getNetworkTypeList(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return networkTypeList;
    }
    @GetMapping("/network/list")
    public List<NetworkVo> getNetworkList(){
        return networkService.getNetworkList();
    }

    @PostMapping("/network")
    public String createNetwork(@RequestBody NetworkDto dto){
        return networkService.createNetwork(dto);
    }

    @PutMapping("/network")
    public String updateNetwork(){
        return null;
    }

    @DeleteMapping("/network")
    public Boolean deleteNetwork(String networkId){
        return networkService.deleteNetwork(networkId);
    }

    @GetMapping("/subnet/list")
    public List<SubnetVo> getSubnetList(@RequestParam("networkId")String networkId){
        return networkService.getSubnetList(networkId);
    }

    @PostMapping("/subnet")
    public String createSubnet(SubnetDto subnetDto){
        return networkService.createSubnet(subnetDto);
    }

    @DeleteMapping("/subnet/")
    public void deleteSubnet(List<String> subnetIdList){
         networkService.deleteSubnet(subnetIdList);
    }

    @GetMapping("/server/list")
    public List<ServerVo> getServerList(@RequestParam("networkId")String networkId){
        return serverService.getServerList(networkId);
    }

    @PostMapping("/server")
    public String createServer(@RequestBody ServerDto serverDto){
        return serverService.createServer(serverDto);
    }

    @PutMapping("/server")
    public String updateServer(){
        return null;
    }

    @DeleteMapping("/server")
    public Boolean deleteServer(String serverId){
        return serverService.deleteServer(serverId);
    }

    @GetMapping("/flavor/list")
    public List<FlavorVo> getFlavorList(){
        return flavorService.getFlavorList();
    }
}
