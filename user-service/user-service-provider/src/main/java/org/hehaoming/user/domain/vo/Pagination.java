package org.hehaoming.user.domain.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pagination<T> {
    private int pageNum;//第几页
    private int pageSize;//每页几行

    private int pages;//总页数

    @ApiModelProperty(value = "总行数", hidden = true)
    private Long total;

    @ApiModelProperty(value = "行数据", hidden = true)
    private List<T> rows;

}

