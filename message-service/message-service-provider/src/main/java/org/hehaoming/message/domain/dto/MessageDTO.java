package org.hehaoming.message.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {

    private Integer id;
    private Integer receiverId;
    private String content;
    private Date gmtCreate;
    private Date gmtModified;
    private Boolean isDeleted;

}
