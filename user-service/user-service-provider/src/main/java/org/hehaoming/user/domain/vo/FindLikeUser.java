package org.hehaoming.user.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(description = "无需查询的字段不用传")
@AllArgsConstructor
public class FindLikeUser {


    private int pageNum;

    private int pageSize;

    private Integer id;

    private String phone;

    private String name;

    private Boolean isDeleted;
}
