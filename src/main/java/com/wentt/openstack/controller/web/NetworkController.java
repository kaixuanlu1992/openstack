package com.wentt.openstack.controller.web;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.dto.NetworkUpdateDto;
import com.wentt.openstack.controller.dto.SubnetDto;
import com.wentt.openstack.controller.vo.ImageVo;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.RouterVo;
import com.wentt.openstack.controller.vo.SubnetVo;
import com.wentt.openstack.service.NetworkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    public List<NetworkVo> getNetworkList(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return networkService.getNetworkList();
    }

    @PostMapping("/network")
    @ApiOperation("创建网络")
    public String createNetwork(@RequestBody NetworkDto dto) throws Exception {
        return networkService.createNetwork(dto);
    }

    @PutMapping("/network")
    @ApiOperation("更新网络")
    public Boolean updateNetwork(@RequestBody NetworkUpdateDto dto) {
        networkService.updateNetwork(dto);
        return true;
    }

    @DeleteMapping("/network")
    @ApiOperation("删除网络")
    public Boolean deleteNetwork(String networkId) {
        return networkService.deleteNetwork(networkId);
    }

    @GetMapping("/subnet/list")
    @ApiOperation("获取子网列表")
    public List<SubnetVo> getSubnetList(@RequestParam("networkId") String networkId) {
        List<SubnetVo>rs=new ArrayList<>();
        SubnetVo subnetVo=new SubnetVo();
        subnetVo.setId("1111111");
        subnetVo.setName("2222222222");
        rs.add(subnetVo);
        //return rs;
        return networkService.getSubnetList(networkId);
    }

    @PostMapping("/subnet")
    @ApiOperation("创建子网")
    public String createSubnet(@RequestBody SubnetDto subnetDto) {
        //return "111";
        return networkService.createSubnet(subnetDto);
    }

    @GetMapping("/project/list")
    @ApiOperation("项目列表")
    public List<ImageVo> getProjectList() {
        return networkService.getProjectList();
    }

    @GetMapping("/role/list")
    @ApiOperation("角色列表")
    public List<ImageVo> getRoleList() {
        return networkService.getRoleList();
    }

    @GetMapping("/physical/list")
    @ApiOperation("物理网络列表")
    public List<String> getPhysicalList() {
        return networkService.getPhysicalList();
    }

    @DeleteMapping("/subnet/")
    @ApiOperation("删除子网")
    public Boolean deleteSubnet(String id) {
        return networkService.deleteSubnet(id);
    }



//    @GetMapping("/router/list")
//    @ApiOperation("获取路由器列表")
//    public List<RouterVo> getRouterList() {
//        return networkService.createRouter(routerVo);
//    }
}
