package org.hehaoming.user.domain.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Pagination<T> {
    private int page;
    private int size;
    @ApiModelProperty(value = "总行数", required = false)
    private int total;
    @ApiModelProperty(value = "行数据", hidden = true)

    private List<T> rows;

}

