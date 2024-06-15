package com.jieming;

public class AboutLock {

    /*
    * 什么是重入锁（ReentrantLock）?说下ReentrantLock和Synchronized关键字差别
    *   ReentrantLock（可重入锁）是java中一种显式锁的实现，允许线程多次重复获得同一把锁。
    *   表示同一个线程可以多次获得同一个锁而被不阻塞。
    * ReentrantLock和Synchronezed的区别：
    *   1 可中断性：ReentrantLock提供了 lockinterruptiably（）方法，，允许线程在等待锁的过程中可以响应式中断
    *     synchronized不支持这种操作
    *   2 公平性选择：ReentrantLock可以做公平锁使用，即等待锁时间最长的线程将获得锁，而synchronized不支持这种操作
    *   3 条件变量选择：ReentrantLock可以配合Condition对象实现精确的线程等待/通知机制，而synchronized不支持这种操作
    *   4 尝试获取锁：ReentrantLock提供了tryLock（）方法，能够尝试获取锁而步阻塞，而synchronized无法实现非阻塞使获取锁
    *   5 锁的释放：使用synchronized，锁的释放由jvm隐式管理，而ReentrantLock需要显示的调用unlock（）释放锁，这样能够更灵活的
    *     实现锁的释放
    *
    * */
}
