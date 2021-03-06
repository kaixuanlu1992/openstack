package com.wentt.openstack.service;

import com.wentt.openstack.controller.dto.UserDto;
import com.wentt.openstack.controller.vo.UserVo;

import java.util.List;

public interface TUserService {
    String createUser(UserDto userDto);

    List<UserVo> getUserList();

    Boolean deleteServer(String userId);

    String updateUser(UserDto userDto);

    Boolean enableUser(String userId, Boolean enable);

    String updatePassword(UserDto userDto);
}
