package com.graduate.backend.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class StrUtil {
    //获取uid
    public static int getId(HttpServletRequest request){
        String id = request.getHeader("id");
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(id).matches()){
            return Integer.valueOf(id);
        }else return -1;
    }
}
