package com.example.ming.config.jwt;

/**
 * JWT常量
 * @date 2019/1/2
 * @Varsion 1.0
 */
public interface JwtConstants {
    //AUTH_HEADER    是HTTPHeader请求的参数
    String AUTH_HEADER = "Authorization";
    //SECRET         是具体的加密算法
    String SECRET = "defaultSecret";
    //AUTH_PATH      是提供给客户端获取JWT参数的接口/需要提供正确的用户名以及密码
    String AUTH_PATH = "/attackApi/auth";
    //EXPIRATION     是计算JWT过期时间需要用到的
    Long EXPIRATION = 604800L;
}



