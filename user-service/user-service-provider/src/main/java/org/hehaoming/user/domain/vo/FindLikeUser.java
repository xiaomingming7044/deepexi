package org.hehaoming.user.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel(description = "无需查询的字段不用传")
@AllArgsConstructor
@NoArgsConstructor
public class FindLikeUser {

    @Builder.Default
    private int pageNum=1;
    @Builder.Default
    private int pageSize=5;

    private Integer id;

    private String phone;

    private String name;

    private Boolean isDeleted;
}
