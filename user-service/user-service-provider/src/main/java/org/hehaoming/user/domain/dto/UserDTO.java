package org.hehaoming.user.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO
{

    private Integer id;

    private String phone;

    private String name;

    private String pwd;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isDeleted;
}
