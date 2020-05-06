package org.hehaoming.user.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hehaoming.user.constant.Constant;
import org.hehaoming.user.domain.dto.UserDTO;
import org.hehaoming.user.domain.query.AddUser;
import org.hehaoming.user.domain.query.FindUser;
import org.hehaoming.user.domain.query.UpdateUser;
import org.hehaoming.user.domain.vo.FindLikeUser;
import org.hehaoming.user.domain.vo.Pagination;
import org.hehaoming.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags={"用户增删改查"})
@ApiResponses({
        @ApiResponse(code = -2, message = "业务逻辑异常"),
        @ApiResponse(code = -1, message = "校验失败"),
        @ApiResponse(code = 1, message = "成功")
})
public class UserController {


//    private final RabbitMQDemoServiceImpl mqDemoService;

    @Resource
    private UserMapper userMapper;

//    @Autowired
//    public UserController(RabbitMQDemoServiceImpl mqDemoService) {
//        this.mqDemoService = mqDemoService;
//    }

//    @GetMapping("greeting")
//    public String sayHello(String str) {
//        mqDemoService.produce(str);
//        return "welcome!";
//    }

    @PostMapping("findLikeUser")
    @ApiOperation(value = "模糊查询，无需查询的字段不用传")
    public Pagination<UserDTO> findLikeUser(@RequestBody FindLikeUser findLikeUser) {
        Page page =  PageHelper.startPage(findLikeUser.getPage(),findLikeUser.getSize());
        FindUser findUser = new FindUser();
        BeanUtils.copyProperties(findLikeUser,findUser);
        List<UserDTO> userDTOList = userMapper.findLikeUser(findUser);

        return new Pagination(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), userDTOList);
    }

    @PostMapping("addUser")
    @ApiOperation(value = "添加用户")
    public String addUser(@Valid @RequestBody AddUser addUser) throws Exception {
        if (userMapper.exactFindUser(new FindUser(null, addUser.getPhone(), null, false)).size() >= 1) {
            throw new Exception(Constant.PHONE_IS_REGIST);
        }
        userMapper.saveUser(addUser);
        return Constant.SUCCESS;
    }

    @PostMapping("deleteUser")
    @ApiOperation(value = "根据id批量删除用户")
    public String deleteUser(@RequestBody @NotNull List<Integer> ids) throws Exception {
        if(ids.size() == 0){
            throw new Exception(Constant.NO_IDS);
        }
        userMapper.deleteUser(ids);
        return Constant.SUCCESS;
    }

    @PostMapping("updateUser")
    @ApiOperation(value = "根据用户id更改用户信息")
    public String updateUser(@Valid @RequestBody UpdateUser updateUser) throws Exception {
        List<UserDTO> list = userMapper.exactFindUser(new FindUser(updateUser.getId(),null,null,false));
        if (list.size() != 1) {
            throw new Exception(Constant.ID_NO_FOUND);
        }
        UserDTO oldUser = list.get(0);

        //改手机号码前，查询新号码是否已被注册
        if (!oldUser.getPhone().equals(updateUser.getPhone()) && userMapper.exactFindUser(new FindUser(null,updateUser.getPhone(),null,false)).size() > 0) {
            throw new Exception(Constant.PHONE_IS_REGIST);
        }
        userMapper.updateUser(updateUser);
        return Constant.SUCCESS;
    }

}
