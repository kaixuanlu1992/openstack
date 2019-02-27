package com.wentt.openstack.dao;

import com.wentt.openstack.controller.dto.NetworkServerCountDto;
import com.wentt.openstack.model.TNetworkServerMap;
import com.wentt.openstack.model.TNetworkServerMapExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TNetworkServerMapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int countByExample(TNetworkServerMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int deleteByExample(TNetworkServerMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int insert(TNetworkServerMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int insertSelective(TNetworkServerMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    List<TNetworkServerMap> selectByExample(TNetworkServerMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TNetworkServerMap record, @Param("example") TNetworkServerMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_network_server_map
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TNetworkServerMap record, @Param("example") TNetworkServerMapExample example);

    List<NetworkServerCountDto> getNetworkServerCount();
}