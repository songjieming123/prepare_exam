package com.jieming;

public class AboutHashMap {

    /*
    * 1 hashmap如何解决hash冲突的？
    *   答：a 通过链地址法，将拥有相同hash值的key放在一起
    *      b 在生成hash值的时候，将hashcode的高16 为和低16为进行异或，使hash值更加均匀，以减少hash冲突
    *      c 如果产生了hash 冲突，链表太长的话，将链表转为红黑树结构，方便查找数据
    */

    /*
    * 2 能否使用任何类作为map的key？
    *   答：结论可以使用任何类作为 Map 的 key，但是有前提：
    *     1 如果类重写了 equals() 方法，也应该重写 hashCode() 方法。
    *     2 如果一个类没有使用 equals()，不应该在 hashCode() 中使用它。
    *     3 用户自定义 Key 类最佳实践是使之为不可变的，这样 hashCode() 值可以被缓存起来，
    *     拥有更好的性能。不可变的类也可以确保 hashCode()和 equals() 在未来不会改变，这样就会解决与可变相关的问题了
    * */


    /*
    * 3 为什么HashMap中String、Integer这样的包装类适合作为K？--主要是保证hash值不变
    *  答： 1 String、Integer 这些包装类使被final修饰，不可变性，保证key的不可更改性，不会存在获取hash值不同的情况
    *      2 这些类，内部重写了equals（），hashcode（）方法，不容易出现hash值错误的情况
    * */

    /*
    * 4 如果使要使用Object类作为hashmap的key，应该如何办？ 该答案和第3题的答案相关
    *   答：1 是因为需要计算存储数据的存储位置，需要注意不要试图从散列码计算中排除掉一个对象
    *        的关键部分来提高性能，这样虽然能更快但可能会导致更多的Hash碰撞；
    *      2 重写equals()方法，需要遵守自反性、对称性、传递性、一致性以及对于任何非null的引用值x，
    *        x.equals(null)必须返回false的这几个特性，目的是为了保证key在哈希表中的唯一性；
    * */

    /*
    * 5 HashMap为什么不直接使用hashCode()处理后的哈希值直接作为table的下标？
    *   答：hashCode()处理后的hash值 int类型的32的值，该值很大，而hashmap中的数组长度
    *      在16到初始化默认值）~2 ^30,但是hashmap的数组长度达不到hashmap的最大值
    *      所以hashcode计算出的hash值可能不再数组范围内，从而无法匹配
    *   hashmap的结局方案：
    *     1 获取hashcode的值为 hash1将hash1的值向右移动16为后，获得的hash2，将hash1 和hash2进行异或，获得一个新的hash3值
    *     该值可以降低hash碰撞，使hash值均匀分配
    *     2 因为hashmap的数组长度长度为16等，数组长度保证为2的幂次方，使用hash3值和table.length-1进行异或，相当于使用hash3对
    *       table.length 进行求模运算，解决了hash值不在数组长度方位内的问题
    * */


    /*
    * 6 HashMap 的长度为什么是2的幂次方
    *   答：取余(%)操作中如果除数是2的幂次则等价于与其除数减一的与(&)操作（也就是说
    ×      hash%length==hash&(length-1)的前提是 length 是2的 n 次方；）。” 并且 采
    *     用二进制位操作 &，相对于%能够提高运算效率，
    *   @@@尽量较少碰撞，也就是要尽量把数据分配均匀，每个链表/红黑树长度大致相同
    *
    * */
}
