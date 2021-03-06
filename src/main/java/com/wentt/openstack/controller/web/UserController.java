package com.wentt.openstack.controller.web;

import com.wentt.openstack.controller.dto.UserDto;
import com.wentt.openstack.controller.vo.UserVo;
import com.wentt.openstack.service.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private TUserService tUserService;
    @PostMapping("/user")
    @ApiOperation("创建用户")
    public String createServer(@RequestBody UserDto userDto){
        return tUserService.createUser(userDto);
    }

    @GetMapping("/user/list")
    @ApiOperation("获取用户列表")
    public List<UserVo> getUserList(){
        return tUserService.getUserList();
    }

    @PutMapping("/user")
    @ApiOperation("更新用户")
    public String updateUse(@RequestBody UserDto userDto){
        return tUserService.updateUser(userDto);
    }

    @PutMapping("/user/password")
    @ApiOperation("修改密码")
    public String updatePassword(@RequestBody UserDto userDto){
        return tUserService.updatePassword(userDto);
    }

    @DeleteMapping("/user")
    @ApiOperation("删除用户")
    public Boolean deleteUser(String userId){
        return tUserService.deleteServer(userId);
    }

    @GetMapping("/enable")
    @ApiOperation("禁用用户")
    public Boolean enableUser(String userId,Boolean enable){
        return tUserService.enableUser(userId,enable);
    }
}
