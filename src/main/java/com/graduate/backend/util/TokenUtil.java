package com.graduate.backend.util;

import com.graduate.backend.pojo.User;
import com.graduate.backend.service.impl.TokenServiceImpl;
import io.jsonwebtoken.*;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//token工具类
@Component
public class TokenUtil {

    @Autowired
    private TokenServiceImpl service;

    /**
     * 加密密钥
     */
    private static final String KEY = "final_design";

    /**
     * 生成token
     * @param id    用户id
     * @param userName  用户名
     * @return
     */
    public String createToken(int id,String userName){
        Map<String,Object> header = new HashMap();
        header.put("typ","JWT");
        header.put("alg","HS256");
        //setID:用户ID
        //setExpiration:token过期时间  当前时间+有效时间
        //setSubject:用户名
        //setIssuedAt:token创建时间
        //signWith:加密方式
        JwtBuilder builder = Jwts.builder().setHeader(header)
                .setId(String.valueOf(id))
                .setSubject(userName)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY);

        return builder.compact();
    }

    /**
     * 生成token
     * @param claims   token的实体
     * @param time     token过期的时间长
     * @return 过期的token
     */
    public String refreshToken(Claims claims,long time){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY)
                .compact();
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果 3-token不合法 2-token过期；1-token认证通过；0-token认证失败
     */
    public int verify(String token){
        Claims claims = null;
        try {
            //token过期后，会抛出ExpiredJwtException 异常，通过这个来判定token过期，
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return 2;
        }catch (Exception e)
        {
            return 3;
        }
        //从token中获取用户id，查询该Id的用户是否存在，存在则token验证通过
        int id = Integer.valueOf(claims.getId());
        User user = service.getUserById(id);
        if(user != null){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 设置1s后过期的token
     * @param token  请求头中携带的token
     * @return  新token字符串
     */
    public String destroyToken(String token)
    {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return refreshToken(claims,1000);
    }

    // 获取token保存的id
    public String getIdByToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        }catch (Exception e){
            return "";
        }
        return claims.getId();
    }
}
