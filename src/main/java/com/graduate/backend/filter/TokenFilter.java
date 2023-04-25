package com.graduate.backend.filter;

import com.graduate.backend.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//请求过滤器，验证token
@WebFilter
public class TokenFilter implements Filter {

    private List<String> notFilter ; //不进行验证的url

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter---->init()");

        notFilter = new ArrayList<>();
        notFilter.add("/user/login");
        notFilter.add("/user/register");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();
        String url =  ((HttpServletRequest)servletRequest).getRequestURI();
        if(url != null){
            //直接放行
            if(notFilter.contains(url)||url.contains("/file")){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                //其他请求验证token
                String token = ((HttpServletRequest)servletRequest).getHeader("token");
                if(token!=null&&!token.isEmpty()){
                    //token验证结果
                    int verify  = tokenUtil.verify(token);
                    //验证失败
                    if(verify == 0){
                        map.put("errorMsg","用户信息验证失败");
                    }else if(verify  == 1){
                        //验证成功，放行
                        TokenRequestWrapper wrapper = new TokenRequestWrapper((HttpServletRequest)servletRequest);
                        String id = tokenUtil.getIdByToken(token);
                        wrapper.putHeader("id",id); //获取token的用户id，存放到header
                        filterChain.doFilter(wrapper,servletResponse);
                        return;
                    }else if(verify == 2){
                        map.put("errorMsg","token已过期");
                    } else{
                        map.put("errorMsg","token不合法");
                    }
                }else{
                    //token为空的返回
                    map.put("errorMsg","未携带token信息");
                }
            }
            //验证失败，返回401和错误信息
            JSONObject jsonObject = new JSONObject(map);
            HttpServletResponse response =(HttpServletResponse)servletResponse;
            //设置401状态码
            response.setStatus(401);
            response.setContentType("application/json");
            //设置响应的编码
            response.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw = response.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }

    @Override
    public void destroy() {

    }
}
