package org.hehaoming.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.hehaoming.user.constant.Constant;
import org.hehaoming.user.domain.dto.UserDTO;
import org.hehaoming.user.domain.query.AddUser;
import org.hehaoming.user.domain.query.FindUser;
import org.hehaoming.user.domain.query.UpdateUser;
import org.hehaoming.user.domain.vo.FindLikeUser;
import org.hehaoming.user.domain.vo.Pagination;
import org.hehaoming.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl {

    @Resource
    private UserMapper userMapper;

    public Pagination<UserDTO> findLikeUser(FindLikeUser findLikeUser){
        Page page =  PageHelper.startPage(findLikeUser.getPageNum(),findLikeUser.getPageSize());
        FindUser findUser = new FindUser();
        BeanUtils.copyProperties(findLikeUser,findUser);
        List<UserDTO> userDTOList = userMapper.findLikeUser(findUser);

        return new Pagination(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), userDTOList);
    }

    public String addUser(AddUser addUser)throws Exception{
        if (userMapper.exactFindUser(new FindUser(null, addUser.getPhone(), null, false)).size() >= 1) {
            throw new Exception(Constant.PHONE_IS_REGIST);
        }
        userMapper.saveUser(addUser);
        return Constant.SUCCESS;
    }

    public String deleteUser(List<Integer> ids) throws Exception{
        if(ids.size() == 0){
            throw new Exception(Constant.NO_IDS);
        }
        userMapper.deleteUser(ids);
        return Constant.SUCCESS;

    }

    public String updateUser( UpdateUser updateUser) throws Exception{
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
