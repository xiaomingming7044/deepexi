package org.hehaoming.user.domain.query;

import lombok.AllArgsConstructor;
import org.hehaoming.user.exception.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
@AllArgsConstructor
public class AddUser {
    @Phone
    @ApiModelProperty(value = "手机号码，未添加过的")
    private String phone;
    @NotBlank
    @ApiModelProperty(value = "昵称，NotBlank")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "密码，NotBlank")
    private String pwd;
}
