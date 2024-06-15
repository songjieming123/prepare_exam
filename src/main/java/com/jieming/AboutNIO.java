package com.jieming;

public class AboutNIO {
    /*
    * BIO： blocking IO，就是传统的java.util.io,基于流的模式实现，交互的方式是同步、阻塞的
    *      也就是说在输入流或者输出流时，在读或者写的操作没有完成之前，一直阻塞在那里，他们之间的调用是
    *      可靠的线性顺序
    *      优点：比较简单直观
    *      缺点：io的效率和扩展性很低，容易造成性能瓶颈
    *
    * NIO： non-blocking IO,jdk 1.4 引入的 java.nio包，提供了 channel和buffer等，可以构建多路复用技术
    *
    * AIO：Asynchronous IO，jdk1.7引入的。提供了异步非阻塞的io，基于事件和回调的机制实现。
    *      即应用操作后会直接返回，不会阻塞在那里。当后台处理完成后，操作系统会通知相应的线程进行后续的操作
    *
    * */

    /*
    * 用到nio 的框架netty0
    *
    * */
}
