package com.graduate.backend.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    private int userId;
    private int tableId;
    private String courseName;
    private String teacherName;
    private String address;
    private int dayWeek; //周几
    private int startSlice; //开始时间 第1节
    private int endSlice; //结束时间 第2节
    private int startWeek; //开始时间 第1周
    private int endWeek;//结束时间 第16周
}
