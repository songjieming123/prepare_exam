package com.jieming;

public class AboutJMM {
    /*
    * jmm（java memeory mode）的理解：
    *    一种抽象概念，规定java并发线程中如何处理多线程之间的内存交互，java解决并发程序中最关键的两个问题：线程间可见性和指令重排序
    *
    * 线程间可见性：确保一个线程修改了共享变量的值，其他线程可以立即看到这一改变，没有良好的可见性保证，一个线程对共享变量的修改可能对
    *            其他线程不可变，导致数据不一致
    * 指令重排序：为了提高性能，编译器和处理器经常改变指令的执行顺序（只要这种改变不影响单线程内的程序逻辑），然而，在多线程环境下，这种
    *           指令重排序会导致严重的问题
    *
    *
    * 1 原子性（Automicity）：jmm为了保证基本读取和写入操作的原子性，eg 声明为 volitile的变量，其读和写操作都是原子的
    *
    * 2 可见性（Visibility）：为了确保一个线程对共享变量所做的修改可以被其他线程看到，jvm引入了 volitile 和synchronized关键字
    *
    * 3 有序性（ordering）：即便在无依赖的操作中，jmm也禁止编译器和处理器进行重排序，可以通过 volitile 和synchronized和final字段
    *                     jmm在特定情景下避免重排序
    *
    * happen-before原则：用于描述多线程环境下操作之间的顺序和可见性
    *   1 程序顺序规则：在一个线程内，每个操作都happen-before于该线程的后续操作
    *   2 监视器锁规则：每个锁的解锁动作happen-before于随后对这个锁的加锁
    *   3 volitile变量规则：对任何一个volitile字段的写入操作都happen-before于后续对这个volitile字段的读取
    *   4 线程启动规则：thread的start（）方法先于这个线程的任意一个动作
    *   5 线程终结规则：线程中所有的操作都happen-before于对此线程的终结检测，可以通过thread.join()和thread.isAlive()的返回值
    *                等手段检测
    *   6 中断规则：对于线程的interrupt（）调用happen-before于被中断线程的检测中断时间之前
    *   7 传递规则：如果A happen-before于 B，B happen-before C ，那么 A 一定 happen-before 于C
    *
    *
    *
    *
    *
    *
    *
    *
    * */
}
