### mybatis-3

   [参考](https://my.oschina.net/zudajun?tab=newest&catalogId=3532897)

#### #{}和${}的区别是什么？
    #{}是预编译处理，${}是字符串替换。
    Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；
    Mybatis在处理${}时，就是把${}替换成变量的值。
    使用#{}可以有效的防止SQL注入，提高系统安全性

#### 当实体类中的属性名和表中的字段名不一样 ，怎么办 ？
    别名（直接映射）
    resultMap映射（配置） id property

#### 模糊查询like语句该怎么写?
    java中点击%
    sql中添加：like "%"#{value}"%"

#### 通常一个Xml映射文件，都会写一个Dao接口与之对应，请问，这个Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？
    Dao接口，就是人们常说的Mapper接口，接口的全限名，就是映射文件中的namespace的值，接口的方法名，
    就是映射文件中MappedStatement的id值，接口方法内的参数，就是传递给sql的参数。Mapper接口是没有实现类的，当调用接口方法时，
    接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement，
    举例：com.mybatis3.mappers.StudentDao.findStudentById，
    可以唯一找到namespace为com.mybatis3.mappers.StudentDao下面id = findStudentById的MappedStatement。
    在Mybatis中，每一个<select>、<insert>、<update>、<delete>标签，都会被解析为一个MappedStatement对象。

    不能重载的

    Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象，代理对象proxy会拦截接口方法，
    转而执行MappedStatement所代表的sql，然后将sql执行结果返回

##### Mybatis是如何进行分页的？分页插件的原理是什么？
    Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页，
    可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。

    分页插件的基本原理是使用Mybatis提供的插件接口，实现自定义插件，在插件的拦截方法内拦截待执行的sql，
    然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
#### Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？
    第一种是使用<resultMap>标签，逐一定义列名和对象属性名之间的映射关系。
    第二种是使用sql列的别名功能，将列别名书写为对象属性名，比如T_NAME AS NAME，对象属性名一般是name，小写，
        但是列名不区分大小写，Mybatis会忽略列名大小写，智能找到与之对应对象属性名，你甚至可以写成T_NAME AS NaMe，Mybatis一样可以正常工作。
    有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。
#### 如何执行批量插入?
    foreach
    1、java成批量插入
    2、xml配置层foreach
    <insert id=”insertname” paremeterType="list">
     insert into names (name) values (#{value})
        <foreach item="item" collection="list" separator="," open="(" close=")" index="">
           #{item.id, jdbcType=NUMERIC}
         </foreach>
    </insert>
#### 如何获取自动生成的(主)键值?
    insert 方法总是返回一个int值 - 这个值代表的是插入的行数。
    而自动生成的键值在 insert 方法执行完后可以被设置到传入的参数对象中。
        <insert id=”insertname” usegeneratedkeys=”true” keyproperty=”id” paremeterType="Name">
            insert into names (name) values (#{name})
        </insert>

        Name name = new Name();
        name.setname(“fred”);

        int rows = mapper.insertname(name);
        // 完成后,id已经被设置到对象中
        system.out.println(“rows inserted = ” + rows);
        system.out.println(“generated key value = ” + name.getid());

#### 在mapper中如何传递多个参数?
    1、顺序传输
        UserselectUser(String name,String area)
        <select id="selectUser"resultMap="BaseResultMap">
            select *  fromuser_user_t   whereuser_name = #{0} anduser_area=#{1}
        </select>
    2、@Param
        user selectuser(@param(“username”) string username,  @param(“hashedpassword”) string hashedpassword);
        <select id=”selectuser” resulttype=”user”>
             select id, username, hashedpassword
             from some_table
             where username = #{username}
             and hashedpassword = #{hashedpassword}
        </select>

#### Mybatis动态sql是做什么的？都有哪些动态sql？能简述一下动态sql的执行原理不？
    Mybatis动态sql可以让我们在Xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能。

    9种动态sql标签：trim|where|set|foreach|if|choose|when|otherwise|bind

    执行原理为，使用OGNL(Object-Graph Navigation Language 表达式语言)从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能。

#### Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重复？
    不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复
    毕竟namespace不是必须的，只是最佳实践而已。

    原因就是namespace+id是作为Map<String, MappedStatement>的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。
    有了namespace，自然id就可以重复，namespace不同，namespace+id自然也就不同

#### 为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？
    Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
    而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称之为半自动ORM映射工具。

#### 一对一、一对多的关联查询 ？

    <association property="teacher" javaType="com.lcb.user.Teacher">
        <id property="id" column="t_id"/>
        <result property="name" column="t_name"/>
    </association>
    <collection property="student" ofType="com.lcb.user.Student">
        <id property="id" column="s_id"/>
        <result property="name" column="s_name"/>
    </collection>


#### Mybatis比IBatis比较大的几个改进是什么

    a.有接口绑定,包括注解绑定sql和xml绑定Sql ,
    b.动态sql由原来的节点配置变成OGNL表达式,
    c. 在一对一的时候引进了association,在一对多的时候引入了collection节点,不过都是在resultMap里面配置

#### 接口绑定有几种实现方式,分别是怎么实现的?
    一种是通过注解绑定,就是在接口的方法上面加上@Select@Update等注解里面包含Sql语句来绑定
    另外一种就是通过xml里面写SQL来绑定,在这种情况下,要指定xml映射文件里面的namespace必须为接口的全路径名.

#### 简述Mybatis的插件运行原理，以及如何编写一个插件
    Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件
    Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法

    实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。

#### Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？
    Mybatis仅支持association关联对象和collection关联集合对象的延迟加载
    在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。

    原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，
    拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，
    接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。

    不光是Mybatis，几乎所有的包括Hibernate，支持延迟加载的原理都是一样的

#### Mybatis都有哪些Executor执行器？它们之间的区别是什么？
    SimpleExecutor、ReuseExecutor、BatchExecutor

    SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
    ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map<String, Statement>内，供下一次使用。简言之，就是重复使用Statement对象。
    BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。

#### MyBatis与Hibernate有哪些不同？
    不完全是一个ORM框架
    mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象

    Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发

    mybatis无法做到数据库无关性

    Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。
    缺点是学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。

#### JDBC编程有哪些不足之处，MyBatis是如何解决这些问题的？
    数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
        在SqlMapConfig.xml中配置数据链接池，使用连接池管理数据库链接
    Sql语句写在代码中造成代码不易维护
    向sql语句传参数麻烦
    向sql语句传参数麻烦
#### MyBatis编程步骤是什么样的？
    ① 创建SqlSessionFactory
    ② 通过SqlSessionFactory创建SqlSession
    ③ 通过sqlsession执行数据库操作
    ④ 调用session.commit()提交事务
    ⑤ 调用session.close()关闭会话

#### SqlMapConfig.xml中配置有哪些内容？
    properties（属性）
    settings（配置）
    typeAliases（类型别名）
    typeHandlers（类型处理器）
    objectFactory（对象工厂）
    plugins（插件）
    environments（环境集合属性对象）
    environment（环境子属性对象）
    transactionManager（事务管理）
    dataSource（数据源）
    mappers（映射器）

#### 简单的说一下MyBatis的一级缓存和二级缓存？
    Mybatis首先去缓存中查询结果集，如果没有则查询数据库，如果有则从缓存取出返回结果集就不走数据库。
    Mybatis内部存储缓存使用一个HashMap，key为hashCode+sqlId+Sql语句。value为从查询出来映射生成的java对象

    Mybatis的二级缓存即查询缓存，它的作用域是一个mapper的namespace，即在同一个namespace中查询sql可以从缓存中获取数据。二级缓存是可以跨SqlSession的。

    Mybatis的一级缓存，指的是SqlSession级别的缓存，默认开启；
    Mybatis的二级缓存，指的是SqlSessionFactory级别的缓存，需要配置。
    缓存是针对select来说的。

    一级缓存
    <configuration>
    	<settings>
    		<setting name="localCacheScope" value="SESSION|STATEMENT" />
    	</settings>
    </configuration>
    localCacheScope用于配置一级缓存的范围，默认值是SESSION，表示SqlSession范围；
    如果配置为STATEMENT，则表示SqlSession范围内的一个查询范围，但它并不是一个Statement实例范围。
    STATEMENT举例：查询Student对象发送一次sql查询，紧接着再发一次sql查询关联的Teacher对象，这个完整过程称之为一个查询。

    一级缓存默认开启，且没有全局关闭的配置开关。

    <select ... flushCache="false" useCache="true|false"/>
    flushCache：同时影响了一级、二级缓存，flushCache=true，会导致清空本条sql（当前MappedStatement）的一级、二级缓存，注意是当前的，不影响其他的MappedStatement。

    useCache：配置本条MappedStatement是否使用二级缓存，useCache=true，从二级缓存中获取，没有获取到，才从数据库中获取。

    在执行update、insert、delete、flushCache="true"、commit、rollback、LocalCacheScope.STATEMENT等情况下，一级缓存就都会被清空。

    缓存其实基本数据结构就是一个HashMap，缓存中是否存在缓存数据，依赖key的生成策略。

    二级缓存
    <configuration>
    	<settings>
    		<setting name="cacheEnabled" value="true|false" />
    	</settings>
    </configuration>
    cacheEnabled=true表示二级缓存可用，但是要开启话，需要在Mapper.xml内配置。
    <cache eviction="FIFO" flushInterval="60000" size="512" readOnly="true"/>

    二级缓存通过CachingExecutor来实现，原理是缓存里存在，就返回，没有就调用Executor delegate到数据库中查询。

    FIFO：First In First Out先进先出队列。
    flushInterval="60000"，间隔60秒清空缓存，这个间隔60秒，是被动触发的，而不是定时器轮询的。
    size=512，表示队列最大512个长度，大于则移除队列最前面的元素，这里的长度指的是CacheKey的个数。
    CacheKey的生成策略，和一级缓存相同，id + offset + limit + sql + param value + environment id。
    readOnly="true"，表示任何获取对象的操作，都将返回同一实例对象。如果readOnly="false"，则每次返回该对象的拷贝对象，简单说就是序列化复制一份返回。

    二级缓存有一个非常重要的空间划分策略：

    namespace="com.mybatis3.mappers.TeacherMapper"

    namespace="com.mybatis3.mappers.StudentMapper"

    即，按照namespace划分，同一个namespace，同一个Cache空间，不同的namespace，不同的Cache空间。

    每当执行insert、update、delete，flushCache=true时，二级缓存都会被清空。


#### Mybatis是否可以映射Enum枚举类？
    Mybatis可以映射枚举类，不单可以映射枚举类，Mybatis可以映射任何对象到表的一列上
    映射方式为自定义一个TypeHandler，实现TypeHandler的setParameter()和getResult()接口方法。TypeHandler有两个作用，一是完成从javaType至jdbcType的转换，二是完成jdbcType至javaType的转换，体现为setParameter()和getResult()两个方法，分别代表设置sql问号占位符参数和获取列查询结果。

#### Mybatis映射文件中，如果A标签通过include引用了B标签的内容，请问，B标签能否定义在A标签的后面，还是说必须定义在A标签的前面？
    虽然Mybatis解析Xml映射文件是按照顺序解析的，但是，被引用的B标签依然可以定义在任何地方，Mybatis都可以正确识别。

    原理是，Mybatis解析A标签，发现A标签引用了B标签，但是B标签尚未解析到，尚不存在，此时，Mybatis会将A标签标记为未解析状态，然后继续解析余下的标签，包含B标签，待所有标签解析完毕，Mybatis会重新解析那些被标记为未解析的标签，此时再解析A标签时，B标签已经存在，A标签也就可以正常解析完成了

#### 简述Mybatis的Xml映射文件和Mybatis内部数据结构之间的映射关系？
    Mybatis将所有Xml配置信息都封装到All-In-One重量级对象Configuration内部。在Xml映射文件中，<parameterMap>标签会被解析为ParameterMap对象，其每个子元素会被解析为ParameterMapping对象。<resultMap>标签会被解析为ResultMap对象，其每个子元素会被解析为ResultMapping对象。每一个<select>、<insert>、<update>、<delete>标签均会被解析为MappedStatement对象，标签内的sql会被解析为BoundSql对象。











