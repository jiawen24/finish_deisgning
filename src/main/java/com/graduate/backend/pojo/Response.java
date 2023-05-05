package com.graduate.backend.pojo;

import lombok.*;
import org.apache.ibatis.annotations.ConstructorArgs;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String status; //请求结果
    private String msg; //信息

    //成功
    public void setSuccess(){
        status = "success";
    }
    //失败
    public void setFailed(){
        status = "failed";
    }
}
