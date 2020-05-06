package org.hehaoming.user.mapper;


import org.hehaoming.user.domain.dto.UserDTO;

import org.hehaoming.user.domain.query.FindUser;
import org.hehaoming.user.domain.query.UpdateUser;
import org.apache.ibatis.annotations.*;
import org.hehaoming.user.domain.query.AddUser;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO `user`(`phone`,`name`,`pwd`) VALUES(#{phone},#{name},#{pwd})")
    @Options(useGeneratedKeys = true)
    void saveUser(AddUser addUserVO);

    @Select("<script> " +
            "SELECT * FROM `user` where 1=1 " +
            "<if test='id!=null'>" +
            "and id = #{id} " +
            "</if>" +
            "<if test='phone!=null and phone!=\"\"'>" +
            "and `phone` like '${phone}%' " +
            "</if>" +
            "<if test='name!=null and name!=\"\"'>" +
            "and `name` like '${name}%' " +
            "</if>" +
            "</script>")
    List<UserDTO> findLikeUser(FindUser findUser);



    @Select("<script> " +
            "SELECT * FROM `user` where 1=1 " +
            "<if test='id!=null'>" +
            "and id = #{id} " +
            "</if>" +
            "<if test='phone!=null'>" +
            "and `phone` = '${phone}' " +
            "</if>" +
            "<if test='name!=null'>" +
            "and `name` = '${name}' " +
            "</if>" +
            "<if test='isDeleted!=null'>" +
            "and `is_deleted` = '${isDeleted}' " +
            "</if>" +
            "</script>")
    List<UserDTO> exactFindUser(FindUser findUser);

    @Update("<script> " +
            "Update `user` SET `is_deleted`=1 WHERE `id` in" +
            "<foreach collection='list' index='index' item='item'  open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    void deleteUser(List<Integer> ids);

    @Update("Update `user` SET phone=#{phone}, name=#{name}, pwd=#{pwd} WHERE `id` = ${id}")
    void updateUser(UpdateUser updateUser);

}
