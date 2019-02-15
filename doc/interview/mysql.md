### canal订阅数据同步

    canal模拟mysql slave的交互协议，伪装自己为mysql slave，向mysql master发送dump协议
    mysql master收到dump请求，开始推送binary log给slave(也就是canal)
    canal解析binary log对象(原始为byte流)

    聊我之前做的一个数据同步的项目，大概内容是订阅 MySQL Binlog，sink 到搜索索引、分库分表以及业务事件订阅流中
    为什么数据同步里选择了 xxxx 开源项目，优势在哪？
    订阅分库分表的 Binlog 怎么订阅？
    分库分表的数据源中假如存在主键冲突要怎么解决？
    怎么保证下游对 Binlog 的消费顺序？
    如何在下游保证消费时的事务原子性？

### 配置中心怎么做服务发现的？怎么做 failover 的？