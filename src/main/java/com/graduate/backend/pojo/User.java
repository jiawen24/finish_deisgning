package com.graduate.backend.pojo;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//用户实体类
public class User {
    private Integer id; //id
    private String username; //名字
    private String password;//密码
    private String school; //学校
    private String major; //专业
    private String avatar;//头像路径
}
