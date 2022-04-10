package com.example.manage.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.manage.elastic.ExampleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description token相关
 * @Date 2020/4/22 15:26
 * @Created by dwx
 */
@Component("tokenManager")
public class TokenManager {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建token
     *
     * @param user
     * @return
     */
    public String createToken(ExampleUser user) {
        String token = UUID.randomUUID().toString();
        String vilToken = String.format("%s_%s", token);
        //存到redis中并设置过期时间
        //存对象时需要将值转为json
        stringRedisTemplate.opsForValue().set(vilToken, JSON.toJSONString(user), 8L, TimeUnit.HOURS);
        return vilToken;
    }

    /**
     * 获取token
     *
     * @param authorization
     * @return
     */
    public String getTokenValue(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(authorization);

    }

    /**
     * @param authorization
     * @return
     */
    public ExampleUser getAuthUser(String authorization) throws Exception {
        String tokenValue = getTokenValue(authorization);
        if (StringUtils.isEmpty(authorization)) {
            throw new Exception("获取权限异常");
        }
        return JSONObject.parseObject(authorization, ExampleUser.class);



    }


}
