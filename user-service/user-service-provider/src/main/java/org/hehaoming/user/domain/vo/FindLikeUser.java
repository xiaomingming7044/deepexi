package org.hehaoming.user.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "无需查询的字段不用传")
public class FindLikeUser {

    private int page;

    private int size;

    private Integer id;

    private String phone;

    private String name;

    private Boolean isDeleted;
}
