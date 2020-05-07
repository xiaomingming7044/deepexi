package org.hehaoming.message.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
@AllArgsConstructor
public class AddMessage {

    @NotNull
    @ApiModelProperty(value = "接收用户ID")
    private Integer receiverId;

    @NotBlank
    @ApiModelProperty(value = "消息内容")
    private String content;


}
