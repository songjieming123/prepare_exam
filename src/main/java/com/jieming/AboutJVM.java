package com.jieming;

public class AboutJVM {
    /*
    * 1 讲一下java虚拟机
    * jvm内存分为：栈、堆、程序计数器、元空间，以及本地方法栈
    *
    * 元数据区：本质和永久代类似，都是对jvm规范中方法区的实现，元空间和永久代的最大区别：
    *         元空间不不再虚拟机中，而是使用本地内存
    * 虚拟机栈：每个线程都有一个线程栈，随线程的创建而创建，每个方法会创建一个栈帧，栈帧中
    *         存放的是局部变量，操作数栈和方法出口等
    * 程序计数器：记录前线程执行的字节码行号
    * 堆：所有线程共有，所有对象和数组都在堆上分配，该部分空间可以通过gc进行回收
    *    唯一用途是存放对象实例
    *
    *
    * 常见的gc算法：
    * 1 复制
    * 2 标记-清除
    * 3 标记--复制
    * 4 分代回收算法：将内存划分为新生代和老年代，分配的依据是对象的生存周期（即对象经历过gc的次数）
    *              在新生代申请内存，每经历一次gc回收，对象年龄+1,当年龄超过一定的值（默认是15），
    *              该对象会进入到老年代，设置放入老年代的gc代数 -XX:MaxTenuringThreshold
    *
    * jvm 有哪些垃圾回收器
    *    1 serial垃圾回收器--串行回收器，可能会产生较长的停顿，只有一个线程进行垃圾回收
    *    2 parNew --serial回收器的多线程版本
    *    3 parallel-- parallel Scavenge 类似parnew， parallel更关注系统的吞吐量
    *    4 parallel old -- 使用多线程和“标记－整理”算法。
    *    5 cms收集器 --一种以获取最短回收停顿时间为目标的收集器
    *    6 G1 收集器-- 一款面向服务器的收集器，主要针对配备多颗处理器以及大容量内存的机器
    *                 通过极高的效率满足GC停顿时间的同时，还具备高吞吐量的新能特征
    *
    *
    *
    * G1回收器的特色
    *   引入分区的思想，弱化了分代的概念
    *   合理利用垃圾收集器哥哥周期的资源，解决了其他收集器的众多缺陷
    *
    * G1相比cms的改进
    *   算法--g1采用 “标记--整理”不会产生空间碎片，在分配大对象时，不会因无法得到连续的空间，
    *   而提前触发一次 FULL GC
    *   停顿时间可控--G1可以通过设置预期停顿时间（Pause Time）来控制垃圾收集时间避免应用雪崩现象
    *   并行与并发 -- G1 能更充分的利用 CPU 多核环境下的硬件优势，来缩短 stop the world 的停顿时间。
    *
    *
    * gc只会对堆进行gc吗：
    *   还会对方法区进行垃圾回收：
    *      方法区是用于存储类信息、常量、静态变量等数据的区域。
    *      虽然方法区中的垃圾回收与堆有所不同，但是同样存在对不再需要的常量、无用的类信息等进行清理的过程
    * */





    /*
    * 关于java并发：
    *   解决线程并发问题的方案：
    *    1 当只有一个线程写，其它线程都是读的时候，可以用volatile修饰变量
    *    2 当多个线程写，那么一般情况下并发不严重的话可以用Synchronized，
    *      Synchronized并不是一开始就是重量级锁，在并发不严重的时候，比如
    *      只有一个线程访问的时候，是偏向锁；当多个线程访问，但不是同时访问，
    *      这时候锁升级为轻量级锁；当多个线程同时访问，这时候升级为重量级锁
    *      所以在并发不是很严重的情况下，使用Synchronized是可以的，
    *      不过Synchronized有局限性，比如不能设置锁超时，不能通过代码释放锁
    *     。ReentranLock 可以通过代码释放锁，可以设置锁超时
    *    3 高并发下，Synchronized、ReentranLock 效率低，因为同一时刻只有一个线程
    *      能进入同步代码块，如果同时有很多线程访问，那么其它线程就都在等待锁。这个
    *      时候可以使用并发包下的数据结构，例如ConcurrentHashMap，LinkBlockingQueue，
    *      以及原子性的数据结构如：AtomicInteger
    *
    *
    * 悲观锁--认为多个事务之间频繁发生冲突，所以在读取数据时都加锁，防止其他事务对数据进行修改
    * 乐观锁--认为多个事务之间基本不发生冲突，都不会修改数据，所以只有真的修改数据的时候才检查数据的版本
    *        如果数据版本匹配才修改数据
    *
    * 乐观锁的实现方式：
    *       1 CAS（compare and swap） java提供了java.util.concurrent.atomic 。
    *       2 版本号控制--增加一个版本号字段记录数据更新时候的版本，每次更新时递增版本号
    *       3 时间戳 --如果当前时间戳大于数据的时间戳，则说明数据已经被其他线程更新，更新失败。
    *
    *
    *
    * volatile关键字：
    *     volatile 关键字仅仅保证了可见性，并没有提供原子性。在实现乐观锁时，如果仅仅依赖 volatile
    *     关键字来保证时间戳的可见性，仍然可能面临以下问题：
    *      复合操作可能存在问题： 如果对时间戳字段的更新是一个复合操作，如读取时间戳、计算新值、更新时间戳，这样的操作并不具备原子性。
    *      在这种情况下，仅仅使用 volatile 是无法保证操作的原子性和线程安全的。可以改用 Atomic 类来保证原子性，这些原子类是使用
    *      CAS (Compare and Swap) 操作来保证数据的原子性。如 AtomicInteger、AtomicLong、AtomicReference 等，
    *      通过这些原子类可以实现 CAS 操作，从而确保对共享变量的原子操作
    *
    *
    * synchronized 和 ReentrantLock 的区别：
    *     1 synchronized 是和 if、else、for、while 一样的关键字，ReentrantLock 是类
    *     2 ReentrantLock 是类，那么它就提供了比 synchronized 更多更灵活的特性，可以被继承、可以有方法
    *       扩展性体现在：
    *         1 ReentrantLock 可以对获取锁的等待时间进行设置，这样就避免了死锁
    *         2 ReentrantLock 可以获取各种锁的信息
    *         3 ReentrantLock 可以灵活地实现多路通知
    *     3 实现原理不一样：
    *         ReentrantLock 底层调用的是 Unsafe 的 park 方法加锁，
    *         synchronized 操作的应该是对象头中 mark word
    *
    *
    * ThreadLocal：
    *    ThreadLocal 是线程共享变量。ThreadLoacl 有一个静态内部类 ThreadLocalMap，其 Key 是 ThreadLocal 对象，
    *   值是 Entry 对象，ThreadLocalMap是每个线程私有的。
    *
    *   set 给ThreadLocalMap设置值。
    *   get 获取ThreadLocalMap。
    *   remove 删除ThreadLocalMap类型的对象。
    * 存在的问题：
    *  1 对于线程池，由于线程池会重用 Thread 对象，因此与 Thread 绑定的 ThreadLocal 也会被重用，造成一系列问题。
       2 内存泄漏。由于 ThreadLocal 是弱引用，但 Entry 的 value 是强引用，因此当 ThreadLocal 被垃圾回收后，value 依旧不会被释放，产生内存泄漏。
    ×
    × 使用完ThreadLocal后，及时调用remove()方法释放内存空间，这样就能避免内存泄漏。
    *
    * 原子性：atomic和synchronized这两个关键字来确保原子性
    * 可见性：synchronized和volatile这两个关键字确保可见性
    * 有序性：happens-before原则来确保有序性
    *
    * */
}
