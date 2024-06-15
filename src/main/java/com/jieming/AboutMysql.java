package com.jieming;

/**
 * https://blog.csdn.net/qq877192055/article/details/131321092
 */
public class AboutMysql {
    /*
    * 一条如何进行优化慢sql：
    *  答: 1 慢查询日志记录慢sql
    *       如何定位慢sql---慢查询日志记录慢sql，可以通过慢查询日志查看慢sql，默认情况数据库使不开启慢查询日志的
    *       使用sql语句 show variables like “slow_query_log%” 可以查看慢查询日志是否打开的状态和 慢查询sql存放的位置
    *       show variables like "long_query_time"  超过这个设定的这个阈值才会记录为慢查询sql
    *
    *      2 使用explain工具查看sql的执行计划
    *        使用explain工具后我们一般重点关注 type，key,rows,filtered以及extra列
    *        type类型:
    *                system > const >eq_ref > ref > range > index > all
    *                const：通过一次索引就能找到数据，一般用于主键或唯一索引作为条件，这类扫描效率极高，，速度非常快
    *                eq_ref：常用于主键或唯一索引扫描，一般指使用主键的关联查询
    *                range：常用于范围查询，比如：between ... and 或 In 等操作
    *                index：全索引扫描
    *        rows:
    *              表示mysql估计要找到我么所需的值需要读取的记录，对于innoDb，该值是一个估算值，不准确
    *        filtered：
    *                表示存储引擎返回的数据在经过过滤后，剩下满足条件的数据的百分比
    *        extra：
    *             using filezort：文件排序，一般是指定的排序和索引排序不一致情况才出现，一般用于order语句排序
    *             Using index ：表示是否用了覆盖索引。
    *             Using temporary: 表示是否使用了临时表,性能特别差，需要重点优化。一般多见于group by语句，或者union语句
    *             Using where : 表示使用了where条件过滤.
    *
    ×        key：
    *            使用的索引，一般配合possible_keys列一起看
    *      3 使用proile分析执行耗时
    *        explain只能看到sql的预估执行计划，，如果要了解sql真正的执行线程状态以及消耗时间，需要开启profie，profile默认shahi关闭的
    *         使用 show variables like “%profile%” 查看profile 状态
    *         使用 set profiling = on， 开启profile
    *         然后执行 sql 语句
    *         在然后执行 show profiles，可以查看最近几条sql语句
    *           ---条数由变量profiling_history_size定义，默认是15
    *
    *        如果要单独查看profile文件中某一条sql的执行消耗： show proile for query id;(此处的id就是show profiles中的query_id)
    *        show profile  cpu,block io for query id;
    *
    *      4 Optimizer Trace分析详情
    *         profile 只能查看到sql的执行耗时，不能看到sql 的真正执行的过程，所以可以通过Optimizer Trace 跟踪执行语句的解析执行优化全过程
    *         a 使用 set optimizer_trace="enabled=on"打开开关
    *         b 然后执行sql语句
    *         c select * from information_schema.optimizer_trace 进行跟踪
    *
    *      5 确认问题然后采取相应的措施
    *          a 多数慢SQL都跟索引有关，比如不加索引，索引不生效、不合理等，这时候，我们可以优化索引
    *          b 我们还可以优化SQL语句，比如一些in元素过多问题（分批），深分页问题（基于上一次数据过滤等），进行时间分段查询
    *          c SQl没办法很好优化，可以改用ES的方式，或者数仓。
    *          e 如果单表数据量过大导致慢查询，则可以考虑分库分表
    *          f 如果存量数据量太大，考虑是否可以让部分数据归档
    ×
    ×
    *
    *  慢查询经典分析：
    *       1 隐式转换 --比如查询条件id是主键，但是id 使int类型，传入的值使“123” ，加了引号，就会隐式的转化为整形
    *           ---在索引列上使用函数计算
    *       2 最左前缀原则 --
    *           ---模糊查询 like “%aa%”
    *       3 深分页问题 -- 延迟关联法
    *            如果是自增主键的情况，可以把limit查询转换为某个位置的查询，
    *              select × from student where id >10000 limit 5
    *       4 in 中元素过多 ，in中元素建议不超过200个， 多了就要分多组执行
    *            in 查询满的原因 -- in的条件超过200，会导致计算代价有问题，会导致mysql索引选择不准确
    *       5 order by走文件排序，导致慢查询
    *         order by排序，分为全字段排序和rowid排序。它是拿max_length_for_sort_data和结果行数据长度对比，
    *                   如果结果行数据长度超过max_length_for_sort_data这个值，就会走rowid排序，相反，
    *                   则走全字段排序
    *                  sort_buffer的大小是由一个参数控制的：sort_buffer_size。
    *                    如果要排序的数据小于sort_buffer_size，排序在sort_buffer内存中完成
    *                     如果要排序的数据大于sort_buffer_size，则借助磁盘文件来进行排序。
    *       6 索引字段上使用is null， is not null，索引可能失效
    *       7 使用 ！= 或者 not in会导致索引失效
    *       8 关联查询，如果左右表的编码格式不一样，会索引会失效
    *       9 将字段多的表拆分成多张表，有的字段使用率高，有些字段使用率低，数据量大时，会由于使用率低的数据查询变慢
    *
    *
    *  简单说下索引是否越多越好？
    *     不是： 1 索引需要占用物理空间，索引越多，占用空间越大
    *           2 会降低增删改查的效率，每次数据更新，会花费大代价时间维护索引
    *           3 在查询的时候，可能会发生锁竞争，降低写入性能
    *
    *
    *  一条sql执行很慢有哪些原因：
    *     1 索引问题
    *         1.01 缺乏索引或者索引不当---可能会造成全表扫描，大型数据集会造成大问题
    *         1.02 索引失效--补充索引失效的场景
    *         1.03 索引过多
    *     2 数据量问题
    *         2.01 数据量过大
    *                 随着数据量的增长，查询性能可能会下降，可以考虑分表，分库或者使用数据库分区
    *         2.02 数据倾斜
    *                 表中数据分布不均，某些分区，索引块的数据过多，导致chaxun变慢
    *     3 查询优化
    *         3.01 查询设计不当
    *                  复杂的连接，子查询都会导致查询性能降低
    *         3.02 没有使用最优的执行计划
    *     4 服务器配置问题
    *         4.01 内存不足
    *                  内存不足导致频繁的裁判I/O，降低数据库查询性能
    *         4.02 cpu限制
    *                  cpu资源不足
    *     5 数据库的设计问题
    *         5.01 表结构设计不合理
    *                 选择不适合的存储引擎类型，过度规范化或者反规范化
    *         5.02 数据类型选择不当
    *                 使用不适合的数据类型会导致索引效率降低
    *
    **/
}
