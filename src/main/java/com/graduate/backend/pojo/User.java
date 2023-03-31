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
    private String name; //名字
    private String school; //学校
    private String major; //专业
}
