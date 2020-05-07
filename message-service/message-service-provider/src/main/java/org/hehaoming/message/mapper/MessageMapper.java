package org.hehaoming.message.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.hehaoming.message.domain.query.AddMessage;

@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO `message`(`receiver_id`,`content`) VALUES(#{receiverId},#{content})")
    @Options(useGeneratedKeys = true)
    void saveUser(AddMessage addMessage);


}
