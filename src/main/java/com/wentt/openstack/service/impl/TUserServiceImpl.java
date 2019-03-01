package com.wentt.openstack.service.impl;

import com.wentt.openstack.controller.dto.UserDto;
import com.wentt.openstack.controller.vo.UserVo;
import com.wentt.openstack.service.TUserService;
import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TUserServiceImpl implements TUserService {
    @Override
    public String createUser(UserDto userDto) {
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        return os.identity().users().create(
                Builders.identityV2().user()
                .name(userDto.getName())
                        .email(userDto.getEmail())
                        .enabled(userDto.getEnable())
                        .tenantId(userDto.getTenantId())
                        .password(userDto.getPassword())
                .build()
        ).getId();
    }

    @Override
    public List<UserVo> getUserList() {
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        List<UserVo> userList=new ArrayList<>();
         os.identity().users().list().forEach(item->{
            UserVo userVo=new UserVo();
            userVo.setEmail(item.getEmail());
            userVo.setEnable(item.isEnabled());
            userVo.setId(item.getId());
            userVo.setName(item.getName());
            userVo.setTenantName(os.identity().tenants().get(item.getTenantId()).getName());
            userList.add(userVo);
        });
        return userList;
    }

    @Override
    public Boolean deleteServer(String userId) {
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        return os.identity().users().delete(userId).isSuccess();
    }

    @Override
    public String updateUser(UserDto userDto) {
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        return os.identity().users().update(
                Builders.identityV2().user().id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .enabled(userDto.getEnable())
                .tenantId(userDto.getTenantId())
                .password(userDto.getPassword())
                .build()).getId();
    }
}
