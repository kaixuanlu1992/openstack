package com.wentt.openstack.service;

import com.wentt.openstack.controller.dto.NetworkDto;
import com.wentt.openstack.controller.dto.NetworkUpdateDto;
import com.wentt.openstack.controller.dto.SubnetDto;
import com.wentt.openstack.controller.vo.ImageVo;
import com.wentt.openstack.controller.vo.NetworkVo;
import com.wentt.openstack.controller.vo.RouterVo;
import com.wentt.openstack.controller.vo.SubnetVo;

import java.util.List;

public interface NetworkService {
    /**
     * 获取网络信息
     *
     * @return
     */
    List<NetworkVo> getNetworkList();

    /**
     * 获取子网信息
     *
     * @param networkId
     * @return
     */
    List<SubnetVo> getSubnetList(String networkId);

    /**
     * 创建网络
     *
     * @param dto
     * @return
     */
    String createNetwork(NetworkDto dto) throws Exception;

    /**
     * 删除网络
     *
     * @param networkId
     * @return
     */
    Boolean deleteNetwork(String networkId);

    /**
     * 删除子网
     * @param subnetIdList
     * @return
     */
    Boolean deleteSubnet(String subnetIdList);

    /**
     * 创建子网
     * @param subnetDto
     * @return
     */
    String createSubnet(SubnetDto subnetDto);

    /**
     * 更新网络
     * @param dto
     * @return
     */
    void updateNetwork(NetworkUpdateDto dto);


    /**
     * 获取项目列表
     * @return
     */
    List<ImageVo> getProjectList();

    /**
     * 获取项目列表
     * @return
     */
    List<ImageVo> getRoleList();

    /**
     * 获取物理网络列表
     * @return
     */
    List<String> getPhysicalList();
}
