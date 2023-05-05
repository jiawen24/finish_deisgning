package com.graduate.backend.pojo;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {
    private int noteId;
    private int userId;
    private String title;
    private String content;
    private Date updateTime;
}
