package com.graduate.backend.pojo;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resource {
    private int userId;    //用户id
    private int fileId;    //文件id
    private String fileName;//文件名
    private String filePath;//文件路径 ->"id_name"
}
