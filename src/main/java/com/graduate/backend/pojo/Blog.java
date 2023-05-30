package com.graduate.backend.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Blog {
    private int blogId;
    private int userId;
    private String title;
    private String major;
    private String content;

    private String username;
}
