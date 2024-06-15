package com.jieming;

public class AboutSpring {
    /*
    * spring中bean的作用域：
    *    1 singleton
    *    2 prototype
    *    3 reqest
    *    4 session
    *    5 application
    *    6 websocket
    *
    * springAOP的实现方式：
    *   通过动态代理实现，为spring的某个bean配置了切面，那么spring在创建该bean的时候实际会创建一个gaibean的代理对象
    *   后续对该bean中方法的调用，实际调用的使代理类的代理方法
    *   两种代理方式：jdk的动态代理和cglib代理方式
    *     spring默认采用jdk动态代理的方式实现aop，InvocationHandler，实现该接口，然后实现invoke方法
    *     Proxy，通过newProxyInstance（）方法返回代理对象，
    *
    *
    * */
}
