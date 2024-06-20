package com.jieming;

/**
 * https://mp.weixin.qq.com/s/nl-ymmv3ZGz2YkGlQLEJvw?from=singlemessage&isappinstalled=0&scene=1&clicktime=1718716596&enterid=1718716596
 */
public class AboutRedis {
    /*
    * redis的使用场景？
    *   1 做缓存，可以八热点数据放在保存在内存中，加快应用程序的访问速度
    *
    *   2 分布式锁
    *       redisTemplate.opsForValue().setIfAbsent()命令 + lua脚本
    *
    *   3 会话存储  分布式下替代session
    *
    *   4 做排行榜
    *     zincrby  leaderboard  1 “article1”
    * */


    /*
    * redis实现接口的幂等性：
    *  1 最简单的实现 分布式锁只是加一个锁就可以，只需要一个对输入参数和接口方法组成的唯一id就行
    *
    *
    *
    *
    *
    *
    * */

    /*
    * 讲述下redis为什么那么快，官方基于单线程的基准测试结果是：单线程 吞吐量使10w/s
    *   1 redis是一款基于内存的数据库，大多数操作都是在数据库中完成，且采用了高效的数据结构，
    *     因此redis的瓶颈是内存大小和网络带宽
    *   2 采用单线程模型，可以避免多线程间的竞争，避免多线程上下文之间的切换带来的时间上和性能上的开销
    *   3 采用多路复用技术处理客户端的socket的请求
    *
    *
    * */




    /*
    * 接口防抖（防重复提交的方案）： 1 防用户手抖--多次提交（可以前端按钮控制） 2 防网络波动抖（只能后端控制）
    * 哪些接口需要防抖：
    *   1 （用户输入）类接口：比如（搜索框输入）、（表单输入）等，（用户输入）往往会（频繁）触发接口请求
    *     每次触发并不一定需要立即发送请求，可以等待用户完成输入一段时间后再发送请求
    *   2 （按钮点击类）接口：比如提交表单、保存设置等，（频繁点击按钮）
    *   3 （滚动加载类）接口：比如下拉刷新、上拉加载更多等
    * 接口防抖--采用注解annotation的方式，
    *
    *
    *
    *
    *
    * */
}
