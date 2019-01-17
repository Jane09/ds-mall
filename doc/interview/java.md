### String为什么是final类型的，好处
    拒绝被继承，不可变；一旦实例化就不可变
    具体实现是：private final char[] value; 不可变字符数组
    Array变量只是stack上的一个引用，数组的本体结构在heap堆
    数组不可变，不代表数组里面的元素不可变；private限制暴露对外可改；
    好处
    	安全
    	千万不要用可变类型做HashMap和HashSet键值
    	线程安全
    	不同变量名对应的相同字符串，指向的是同一个内存地址；大量使用可以节省空间
### HashMap 源码，实现原理，底层结构
    jdk 1.7,1.6
    位桶+链表实现，即使用链表处理冲突，同一hash值的链表都存储在一个链表里。但是当位于一个桶中的元素较多，即hash值相等的元素较多时，通过key值依次查找的效率较低
    java8+
    位桶+链表+红黑树实现，当链表长度超过阈值（8）时，将链表转换为红黑树，这样大大减少了查找时间
    首先有一个每个元素都是链表（可能表述不准确）的数组，当添加一个元素（key-value）时，就首先计算元素key的hash值，
    以此确定插入数组中的位置，但是可能存在同一hash值的元素已经被放在数组同一位置了，这时就添加到同一hash值的元素的后面，他们在数组的同一位置，但是形成了链表，同一各链表上的Hash值是相同的，所以说数组存放的是链表。而当链表长度太长时，链表就转换为红黑树，这样大大提高了查找的效率。
    多线程下get方法的无线循环
    源码：
    Node<K,V>[] table 数组
    Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
    }

### Class.forName vs ClassLoader
    类型加载
    class.forName
    除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块
    Class.forName(name, initialize, loader)
    带参函数也可控制是否加载static块。并且只有调用了newInstance()方法采用调用构造函数，创建类的对象
    classLoader
    只将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块
### session和cookie的区别和联系，session的生命周期，多个服务部署时session管理

    对象	    信息量大小	    保存时间	                            应用范围	保存位置
    Session	小量,简单的数据	用户活动时间+一段延迟时间(一般为20分钟)	单个用户	服务器端
    Cookie	小量,简单的数据	可以根据需要设定	                    单个用户	客户端
    response传sessionId给客户端 set-cookie
    客户端存储session id的方式
    cookie
    cookie被禁，
        URL重写，sessionId在url的后面；缺点：重写所有，任何静态页面都失效
        表单隐藏字段：表单提交限制
    getSession(true)创建session
    删除：
        HttpSession.invalidate()
        session超时
        服务器停止

    cookie
    会话cookie
    持久cookie

### Java中的队列都有哪些，有什么区别
    FIFO
    Queue继承了Collection；
    Deque继承Queue，实现LinkedList

    Queue非阻塞
    PriorityQueue：有序数组，compare后入队，接口或者comparator
    ConcurrentLinkedQueue: 链表实现，线程安全的CAS实现

    Queue阻塞 实现BlockedQueue
    ArrayBlockingQueue : 数组实现，ReentrantLock实现线程安全
    LinkedBlockingQueue：链表实现，ReentrantLock
    PriorityBlockingQueue: 数组实现，ReentrantLock，compare
    DelayQueue：一个由优先级堆支持的、基于时间的调度队列，PriorityQueue实现，数组，优先级，时间
    SynchronousQueue: 一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制