package com.lionphago.backend.common.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private Long id;  // 学号
    private String username; // 用户名
    private Integer grade; // 年级
    private Integer _class; // 班级
    private String major; // 专业
    private String school; // 学院
}
