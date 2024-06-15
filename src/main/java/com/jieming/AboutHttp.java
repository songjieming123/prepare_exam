package com.jieming;

public class AboutHttp {

    /*
    * springboot 如何将http请求转为https请求
    * 第一步 首先在yml中配置
    *   server:
    *      port: 80
    *      ssl:
    *        key-store: classpath:keystore.jks
    *        key-store-password: yourpassword
    *        keyAlias: tomcat
    *
    * 第二步：确保你有一个密钥库文件（如keystore.jks）并且正确配置了密钥库的路径和密码
    *
    * 第三步：了确保所有的HTTP请求都被自动重定向到HTTPS，你可以在你的Spring Boot应用中添加一个配置类
    *        配置大约见 config下的ServerConfig
    ×
    * */

    /*
    * https请求为何安全
    *      它是在HTTP的基础上，通过使用SSL或TLS协议来实现对信息的加密和身份认证，
    *      客户端和服务器之间的所有数据都是加密的，第三方无法获取和解读其中的内容，以确保用户数据的安全性，
    *      HTTPS协议还可以防止黑客进行网络窃听、中间人攻击等安全威胁
    *
    *     防范非法中间加密代理：
    *       黑客对客户端伪装成服务器，对服务器伪装成客户端，通过非法代理窃取会话数据。
    *       通过何种方式避免非法代理：
    *           1 认证站点：
    *                   客户端向站点发起HTTPS请求，站点返回数字证书。客户端通过数字证书验证所访问的站点是真实的目标站点。
    *           2 协商密钥：
    *                    客户端与站点服务器协商此次会话的对称加密密钥，用于下一阶段的加密传输。
    *           虽然黑客可以获取站点的证书，伪装成站点服务器接收请求，但黑客没有站点服务器私钥，无法与实现客户端实现密钥交换，不能窃取明文的会话数据。
    * */




    /*
    * springboot中的跨域问题
    *    1 @CrossOrigin注解，我们可以在特定的某些接口加上@CrossOrigin注解，表示该接口允许跨域访问。注：未加该注解的接口仍不允许跨域访问
    *      @CrossOrigin(origins =“http://localhost:7060”) 设置了originshiroshi只允许这个路径的跨域，如果不设置，表都允许跨域
    *
    *    2 实现WebMvcConfigurer 接口
    *
    *    4 nginx跨域设置---但是nginx 跨域设置和后端宽裕设置只能保留一个
    *
    * */
}
