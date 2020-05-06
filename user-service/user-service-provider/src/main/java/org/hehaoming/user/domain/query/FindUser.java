package org.hehaoming.user.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUser {

    private Integer id;

    private String phone;

    private String name;

    private Boolean isDeleted;

}
