package com.jieming;

/**
 * https://blog.csdn.net/CNpeaceful/article/details/134988346
 *
 *
 * springboot整合rabbtmq https://blog.csdn.net/JavaTeachers/article/details/110820557
 */
public class AboutDistributedTransaction {

    /*
    * 项目中是否用到分布式事务，以及你是如何解决的？
    *   8种方案：
    *         1 2pc
    *         2 3pc
    *         3 TCC
    *         4 本地消息异步确认
    *         5 可靠消息的最终一致性
    *         6 最大努力通知
    *         7 rocketMQ结局分布式事务
    *         8 阿里的seata的分布式解决事务
    *
    *
    *
    *     我们采用的使可靠消息最终一致性的方案，相比于2pc和3pc在性能方面开销大和单点故障问题
    * */


    /*
    * 分布式事务 消息队列--rabbitmq解决方案
    *    1 可靠发送消息
    *       需要保证产生的消息一定发送到mq：
    *                                a 在生产这同一事务中，增加一个消息的冗余表和是否发送成功的状态
    *                                b 利用rabbitmq提供的 publisher/confirm机制，开启确认机制，
    *                                  如果消息正常发送成功，就会收到回执，将状态修改为已发送状态
    *                                c 兜底策略：如果出现异常，定时发送法消息
    *
    *    2 可靠消费
    *      消费者在消费消息的时候出现了异常，会出发mq的的重试机制，会导致死循环，解决消费重试的几种方案：
    *         a 控制重试次数，如果达到最大的重试次数，会把这条消息从队列中移除
    *           如果没有开启手动ack，会采用默认自动ack，会造成消息丢失，一般不会使用
    *           在yml中配置如下：
    *                         rabbitmq :
                                   username: root
                                   password: 123456
                                   virtual-host: /   #表示哪个交换机
                                   piblisher-confirm： true  # 发布确认
                                   listener:
                                       simple:
                                           acknowledge-mode: manual
                                           retry:
                                              enable: true   #b表示开启重试
                                              max-attempts: 3   #最大重试次数为3次
                                              initial-internal: 2000ms  #重试时间间隔为2s
    *
    *
    *
    * */


    /*
    * rabbitmq保证消息顺序的方案：
    *     顺序错乱的场景：
    *                 a 一个queue，多个consumer ---但是每个consumer的执行时间是不固定的，无
    *                                            法保证先读到消息的consumer一定先完成操作
    *                 b consumer多线程消费 ---一个queue对应一个consumer，但是consumer里面进行了多线程消费，
    *                                       这样也会造成消息消费顺序错误
    *    解决方案：
    *          单消费者对单队列：可以给 RabbitMQ 创建多个queue， 每个消费者只消费一个queue，
    *                         生产者根据订单号，把订单号相同的消息放入一个同一个queue。
    *                         这样同一个订单号的消息就只会被同一个消费者顺序消费。
    *          使用多个队列和分区键：可以将相同类型的消息发送到多个队列，并使用分区键 routingKey
    *                          （例如基于消息的某个属性）决定消息要发送到哪个队列。然后，在消费者端，
    *                           针对每个队列都使用单个消费者来保证消息的消费顺序。
    * */



    /*
    * RabbitMQ如何处理消息重复？
    *
    * */
}
