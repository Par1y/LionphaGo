package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("`user`")
public class UserDTO {
    private Long id;  // 学号
    private String username; // 用户名
    private String password; // 口令
    private Integer grade; // 年级
    private Integer _class; // 班级
    private String major; // 专业
    private String school; // 学院
}
