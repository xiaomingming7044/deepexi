package org.hehaoming.message.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.hehaoming.message.domain.dto.MessageDTO;
import org.hehaoming.message.domain.query.AddMessage;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO `message`(`receiver_id`,`content`) VALUES(#{receiverId},#{content})")
    @Options(useGeneratedKeys = true)
    void saveMessage(AddMessage addMessage);

    @Select("select * from message")
    List<MessageDTO> find();

}
