package com.wentt.openstack.controller.web;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.dto.SubnetDto;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.service.NetworkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = "网络接口")
public class NetworkController {
    @Value("${network.type}")
    private List<String> networkTypeList;
    @Autowired
    private NetworkService networkService;


    @GetMapping("/network/type/list")
    @ApiOperation("获取网络类型列表")
    public List<String> getNetworkTypeList(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return networkTypeList;
    }

    @GetMapping("/network/list")
    @ApiOperation("获取网络列表")
    public List<NetworkVo> getNetworkList() {
        return networkService.getNetworkList();
    }

    @PostMapping("/network")
    @ApiOperation("创建网络")
    public String createNetwork(@RequestBody NetworkDto dto) {
        return networkService.createNetwork(dto);
    }

    @PutMapping("/network")
    @ApiOperation("更新网络")
    public String updateNetwork() {
        return null;
    }

    @DeleteMapping("/network")
    @ApiOperation("删除网络")
    public Boolean deleteNetwork(String networkId) {
        return networkService.deleteNetwork(networkId);
    }

    @GetMapping("/subnet/list")
    @ApiOperation("获取子网列表")
    public List<SubnetVo> getSubnetList(@RequestParam("networkId") String networkId) {
        return networkService.getSubnetList(networkId);
    }

    @PostMapping("/subnet")
    @ApiOperation("创建子网")
    public String createSubnet(SubnetDto subnetDto) {
        return networkService.createSubnet(subnetDto);
    }

    @DeleteMapping("/subnet/")
    @ApiOperation("删除子网")
    public void deleteSubnet(List<String> subnetIdList) {
        networkService.deleteSubnet(subnetIdList);
    }
}
