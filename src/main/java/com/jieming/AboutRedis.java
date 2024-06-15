package com.jieming;

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
}
