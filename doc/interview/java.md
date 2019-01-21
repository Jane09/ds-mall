[参考](http://ngudream.com/2017/03/17/java-interview/)
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
    TODO
### Java的内存模型以及GC算法（重点）

#### 内存数据：
    程序计数器：存放下一条运行的指令
    虚拟机栈：存放函数调用堆栈信息
    本地方法栈：存放函数调用堆栈信息
    java堆：存放Java程序运行时所需的对象等数据
    方法区：存放程序的类元数据信息
[内存模型](https://www.cnblogs.com/yueshutong/p/9768298.html)
#### 程序计数器
    每一个线程都必须有一个独立的程序计数器，用于记录下一条要运行的指令。
    各个线程之间的计数器互不影响，独立工作；是一块线程私有的内存空间
    如果当前程序正在执行一个Java方法，则程序计数器记录正在执行的Java字节码地址，如果当前线程正在执行一个Native方法，则程序计数器为空
#### 虚拟机栈
    线程的私有空间，它和Java线程在同一时间创建，它保存方法的局部变量、部分结果，并参与方法的调用和返回
    在Java虚拟机规范中，定义了两种异常与栈空间有关：StackOverflowError 和 OutOfMemoryError。
    在 HotSpot 虚拟机中，可以使用 -Xss 参数（如：-Xss1M）来设置栈的大小。栈的大小直接决定了函数调用的可达深度
    虚拟机栈在运行时使用一种叫做栈帧的数据结构保存上下文数据。在栈帧中，存放了方法的局部变量表、操作数栈、动态连接方法和返回地址等信息。
    每一个方法的调用都伴随着栈帧的入栈操作。相应的，方法的返回则表示栈帧的出栈操作。

### Java7、Java8的新特性
[引用](https://www.zfl9.com/java-jdk7-jdk8.html)
#### Java7
##### 二进制字面量
    二进制使用前缀0b或0B，如：0b110010；
    八进制使用前缀0，如：034；
    十进制没有前缀，就是普通的字面量，如：231；
    十六进制使用前缀0x或0X，如：0x2A7E。

##### 数字_分隔符
    如果一个数值比较大，很长，那么可以使用_来分割，比如：1_000_000_000，表示1,000,000,000；
    只能在数字中间添加_符号，不能在边缘添加及小数点前后添加，在编译过程中，_将被编译器删除。

##### switch 支持 String 字符串
    switch (gender) {
        case "男":
            title = name + " 先生";
            break;
        case "女":
            title = name + " 女士";
            break;
        default:
            title = name;
    }
    JavaCopy
    编译器在编译时先做处理：
    1) 只有一个 case，直接转成 if；
    2) 只有一个 case 和 default，直接转换为 if…else；
    3) 有多个 case，先将 String 转换为 hashCode，然后进行相应的处理，JavaCode 在底层兼容 Java7 以前版本。

    在 case 中，允许使用的类型有byte、char、short、int、枚举常量（JDK1.5）、String（JDK1.7），并且它们都必须为字面常量！

    我们来通过反编译看一下 switch 是如何支持 String 字符串的：

    public class Main {
        public static void main(String[] args) {
            if (args.length < 1)
                System.exit(1);

            switch (args[0]) {
                case "baidu":
                    System.out.println("https://www.baidu.com");
                    break;
                case "google":
                    System.out.println("https://www.google.com");
                    break;
                case "bing":
                    System.out.println("https://www.bing.com");
                    break;
                default:
                    System.out.println("(default)");
            }
        }
    }
    JavaCopy
    # root @ arch in ~/java8-learn/com/zfl9 on git:master x [9:14:45]
    $ javac Main.java

    # root @ arch in ~/java8-learn/com/zfl9 on git:master x [9:14:58]
    $ java Main baidu
    https://www.baidu.com

    # root @ arch in ~/java8-learn/com/zfl9 on git:master x [9:15:03]
    $ jad -p Main.class
    The class file version is 52.0 (only 45.3, 46.0 and 47.0 are supported)
    // Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
    // Jad home page: http://www.geocities.com/kpdus/jad.html
    // Decompiler options: packimports(3)
    // Source File Name:   Main.java

    import java.io.PrintStream;

    public class Main
    {

        public Main()
        {
        }

        public static void main(String args[])
        {
            if(args.length < 1)
                System.exit(1);
            String s = args[0];
            byte byte0 = -1;
            switch(s.hashCode())
            {
            case 93498907:
                if(s.equals("baidu"))
                    byte0 = 0;
                break;

            case -1240244679:
                if(s.equals("google"))
                    byte0 = 1;
                break;

            case 3023936:
                if(s.equals("bing"))
                    byte0 = 2;
                break;
            }
            switch(byte0)
            {
            case 0: // '\0'
                System.out.println("https://www.baidu.com");
                break;

            case 1: // '\001'
                System.out.println("https://www.google.com");
                break;

            case 2: // '\002'
                System.out.println("https://www.bing.com");
                break;

            default:
                System.out.println("(default)");
                break;
            }
        }
    }
    BashCopy
##### catch 同时捕获多个异常
    如果一个catch语句中捕获了多个异常，那么变量e将自动变为final常量。

    多个异常之间使用按位或|操作符连接，如：

    try {
        // TODO
    } catch (ExceptionType1 | ExceptionType2 e) {
        // TODO
    }
    JavaCopy
##### 泛型的类型推断
    在 Java7 之前，我们通常需要这么写：ArrayList<String> list = new ArrayList<String>();
    在 Java7 之后，我们可以这么写：ArrayList<String> list = new ArrayList<>();
##### @SafeVarargs注解
    @SafeVarargs在 JDK7 中引入，主要目的是处理可变长参数中的泛型，此注解告诉编译器：在可变长参数中的泛型是类型安全的。
    可变长参数是使用数组存储的，而数组和泛型不能很好的混合使用；简单的说，数组元素的数据类型在编译和运行时都是确定的，而泛型的数据类型只有在运行时才能确定下来，因此当把一个泛型存储到数组中时，编译器在编译阶段无法检查数据类型是否匹配，因此会给出警告信息："[unchecked] Possible heap pollution from parameterized vararg type T"；
    意思就是：存在可能的堆污染(heap pollution)，即如果泛型的真实数据类型无法和参数数组的类型匹配，会导致 ClassCastException 异常，因此当在可变长参数中使用泛型时，编译器都会给出警告信息。
    忽略这些异常的方法有：
    1) 编译器选项：javac -Xlint:unchecked
    2) 使用注解：@SuppressWarnings("unchecked")
    3) 使用注解：@SafeVarargs（Java7 新增）
##### try-with-resources
    try-with-resources 的实现原理还是依赖于 JDK1.7 中 Throwable 添加的两个方法：
    void addSuppressed(Throwable exception)：将给定异常对象添加到当前异常对象的抑制异常数组中
    Throwable[] getSuppressed()：获取当前异常对象的抑制异常对象数组

    只要一个类实现了 java.lang.AutoCloseable 接口就可以使用try-with-resources进行资源的自动释放
    try-with-resources 语句后面可以有 finally 块，该 finally 块将在 try-with-resources 执行完毕后执行。很好理解，因为 try-with-resources 中已经隐含了一个 finally 语句块了，因此我们自己添加的 finally 块本质上已经没有 finally 的意义了。
    try 块中可以注册多个资源，在离开 try 块时，它们将被按照相反的顺序调用 close() 方法。比如
##### Objects 工具类
    // 比较两个对象，不会产生空指针异常
    public static boolean equals(Object a, Object b);
    // 数组专用，用于比较数组的每个元素
    public static boolean deepEquals(Object a, Object b);

    // return o!=null ? o.hashCode() : 0;
    public static int hashCode(Object o);
    // return Arrays.hashCode(values);
    public static int hash(Object... values);

    // return String.valueOf(o);
    public static String toString(Object o);
    // return o!=null ? String.valueOf(o) : nullDefault;
    public static String toString(Object o, String nullDefault);

    // 使用指定比较器比较两个对象
    public static <T> int compare(T a, T b, Comparator<? super T> c);

    /* 检查参数是否为null，如果是则抛出空指针异常，否则返回传入的值 */
    public static <T> T requireNonNull(T obj);
    public static <T> T requireNonNull(T obj, String message); // 指定异常message
    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier); // 指定异常message的提供者

    // 测试obj是否为null
    public static boolean isNull(Object obj);
    // 测试obj是否不为null
    public static boolean nonNull(Object obj);
#### java8
    Lambda 表达式：对匿名内部类的一个改进，基本可以取代内部匿名类，并且性能比匿名类好得多；
    方法引用：方法引用其实是随 Lambda 产生的语法糖，与 Lambda 联合使用，可以使语言的构造更紧凑简洁，减少冗余代码；
    默认方法：默认方法就是一个在接口里面有了一个实现的方法，使用 default 关键字；
    Stream API：不同于 java.io 中的 Stream 流，新添加的 Stream API（java.util.stream）把真正的函数式编程风格引入到 Java 中；
    Date Time API：加强对日期与时间的处理；
    Optional 类：Optional 类已经成为 Java8 类库的一部分，用来解决空指针异常。
##### Lambda

##### FunctionalInterface 接口

##### 方法引用
    方法引用其实就是 Lambda 表达式的一种简写方式
    四种方法引用类型
    1) 引用静态方法：ClassName::staticMethodName；
    2) 引用实例方法：objectName::methodName（隐式地传入this指针）；
    3) 引用构造方法：ClassName::new；
    4) 引用任意方法：ClassName::methodName（对于实例方法，必须显式地传入this指针）；
    Lambda 表达式的目的仅仅为了调用另一个已存在方法时，适合使用方法引用。
##### 接口默认方法、静态方法

##### Optional容器
    了解决臭名昭著的空指针异常而引入的类，本身是一个容器。Optional 是一个泛型类，为了减少boxing、unboxing带来的性能损失，提供了OptionalInt、OptionalLong、OptionalDouble
##### Stream API
    是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。
##### Spliterator

##### DateTime API

### Java数组和链表两种结构的操作效率，在哪些情况下(从开头开始，从结尾开始，从中间开始)，哪些操作(插入，查找，删除)的效率高
[参考](https://blog.csdn.net/qq_27093465/article/details/52267872)
    数组开辟的是连续的内存空间，是根据基地址和偏移量来算出地址（有乘法和加法运算），然后访问。
    链表前一个数据的地址指向下一个数据地址，如：p = p->next；然后用 *p 访问。按这个说的话，它就一个赋值语句。
### Java内存泄露的问题调查定位：jmap，jstack的使用等等
[参考](https://www.tuicool.com/articles/eu26jeE)

### 动态代理的几种方式
    jdk动态代理

    cglib动态代理
       使用ASM在内存中动态的生成被代理类的子类，使用CGLIB即使代理类没有实现任何接口也可以实现动态代理功能
       运行速度要远远快于JDK的Proxy动态代理

    cglib有两种可选方式，继承和引用

### HashMap的并发问题
    多线程put后可能导致get死循环
    多线程put的时候可能导致元素丢失