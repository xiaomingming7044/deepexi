package org.hehaoming.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hehaoming.user.domain.dto.UserDTO;
import org.hehaoming.user.domain.query.AddUser;
import org.hehaoming.user.domain.query.UpdateUser;
import org.hehaoming.user.domain.vo.FindLikeUser;
import org.hehaoming.user.domain.vo.Pagination;
import org.hehaoming.user.service.RabbitMQDemoServiceImpl;
import org.hehaoming.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = {"用户增删改查"})
@ApiResponses({
        @ApiResponse(code = -2, message = "业务逻辑异常"),
        @ApiResponse(code = -1, message = "校验失败"),
        @ApiResponse(code = 1, message = "成功")
})
public class UserController {


    private RabbitMQDemoServiceImpl mqDemoService;
    private UserServiceImpl userService;



    @Autowired
    public UserController(RabbitMQDemoServiceImpl mqDemoService, UserServiceImpl userService) {
        this.mqDemoService = mqDemoService;
        this.userService = userService;
    }

    @GetMapping("greeting")
    public String sayHello(String str) {
        mqDemoService.produce(str);
        return "welcome!";
    }

    @PostMapping("findLikeUser")
    @ApiOperation(value = "模糊查询，无需查询的字段不用传")
    public Pagination<UserDTO> findLikeUser(@RequestBody FindLikeUser findLikeUser) {
        return userService.findLikeUser(findLikeUser);
    }

    @PostMapping("addUser")
    @ApiOperation(value = "添加用户")
    public String addUser(@Valid @RequestBody AddUser addUser) throws Exception {
        return userService.addUser(addUser);
    }

    @PostMapping("deleteUser")
    @ApiOperation(value = "根据id批量删除用户")
    public String deleteUser(@RequestBody @NotNull List<Integer> ids) throws Exception {
        return userService.deleteUser(ids);
    }

    @PostMapping("updateUser")
    @ApiOperation(value = "根据用户id更改用户信息")
    public String updateUser(@Valid @RequestBody UpdateUser updateUser) throws Exception {
        return userService.updateUser(updateUser);
    }

}
