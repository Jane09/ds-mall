### 什么是 Spring Boot？
    Spring 开源组织下的子项目
    是 Spring 组件一站式解决方案，主要是简化了使用 Spring 的难度，简省了繁重的配置，提供了各种启动器，开发者能快速上手。

### 为什么要用 Spring Boot？
    优点：
        独立运行
            内嵌了各种servlet容器，Tomcat、Jetty等，现在不再需要打成war包部署到容器中，Spring Boot只要打成一个可执行的jar包就能独立运行，所有的依赖包都在一个jar包内。
        简化配置
        自动配置
            Spring Boot能根据当前类路径下的类、jar包来自动配置bean，如添加一个spring-boot-starter-web启动器就能拥有web的功能，无需其他配置。
        无代码生成和XML配置
            也是Spring4.x的核心功能之一
        应用监控
        上手容易
    缺点：
        不了解其核心技术及流程，所以一旦遇到问题就很棘手，而且现在的解决方案也不是很多，需要一个完善的过程
        填坑的过程
        从原始 Spring 项目很难平滑迁移至 Spring Boot 框架上来，因为有些历史老旧的 XML 配置无法通过 Java 来配置，还需要额外的 XML 文件就不是很完美。

### Spring Boot 的核心配置文件有哪几个？它们的区别是什么？
    application 和 bootstrap 配置文件
    application 配置文件这个容易理解，主要用于 Spring Boot 项目的自动化配置。
    bootstrap 配置文件有以下几个应用场景
        使用 Spring Cloud Config 配置中心时，这时需要在 bootstrap 配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息； git
        一些固定的不能被覆盖的属性
        一些加密/解密的场景
        bootstrap 是应用程序的父上下文，也就是说 bootstrap 加载优先于 applicaton。bootstrap 主要用于从额外的资源来加载配置信息，还可以在本地外部配置文件中解密属性。
        boostrap 由父 ApplicationContext 加载，比 applicaton 优先加载
        boostrap 里面的属性不能被覆盖

### Spring Boot 的配置文件有哪几种格式？它们有什么区别？
    .properties 和 .yml
    区别主要是书写格式不同
    .yml 格式不支持 @PropertySource 注解导入配置

### Spring Boot 的核心注解是哪个？它主要由哪几个注解组成的？

    @SpringBootApplication 包含
        @SpringBootConfiguration：组合了 @Configuration 注解，实现配置文件的功能。
        @EnableAutoConfiguration：打开自动配置的功能，也可以关闭某个自动配置的选项，如关闭数据源自动配置功能： @SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })。
        @ComponentScan：Spring组件扫描。

### 开启 Spring Boot 特性有哪几种方式？
    1）继承spring-boot-starter-parent项目
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>1.5.6.RELEASE</version>
        </parent>
    2）导入spring-boot-dependencies项目依赖
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-dependencies</artifactId>
                    <version>1.5.6.RELEASE</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
            </dependencies>
        </dependencyManagement>

        Spring Boot依赖注意点
            属性覆盖只对继承有效
                Spring Boot依赖包里面的组件的版本都是和当前Spring Boot绑定的，如果要修改里面组件的版本，只需要添加如下属性覆盖即可，但这种方式只对继承有效，导入的方式无效。
                <properties>
                    <slf4j.version>1.7.25<slf4j.version>
                </properties>
                如果导入的方式要实现版本的升级，达到上面的效果，这样也可以做到，把要升级的组件依赖放到Spring Boot之前。
                需要注意，要修改Spring Boot的依赖组件版本可能会造成不兼容的问题
            资源文件过滤问题
                使用继承Spring Boot时，如果要使用Maven resource filter过滤资源文件时，资源文件里面的占位符为了使${}和Spring Boot区别开来，
                此时要用@...@包起来，不然无效。另外，@...@占位符在yaml文件编辑器中编译报错，所以使用继承方式有诸多问题，坑要慢慢趟。

### Spring Boot 需要独立的容器运行吗？
    可以不需要，内置了 Tomcat/ Jetty 等容器。

### 运行 Spring Boot 有哪几种方式？
    1）打包用命令或者放到容器中运行

    2）用 Maven/ Gradle 插件运行

    3）直接执行 main 方法运行

### Spring Boot 自动配置原理是什么？
    注解 @EnableAutoConfiguration, @Configuration, @ConditionalOnClass 就是自动配置的核心
    首先它得是一个配置文件，其次根据类路径下是否有这个类去自动配置

   ![参考](https://mp.weixin.qq.com/s/gs2zLSH6m9ijO0-pP2sr9Q)

### Spring Boot 的目录结构是怎样的？

    cn
     +- javastack
         +- MyApplication.java
         |
         +- customer
         |   +- Customer.java
         |   +- CustomerController.java
         |   +- CustomerService.java
         |   +- CustomerRepository.java
         |
         +- order
             +- Order.java
             +- OrderController.java
             +- OrderService.java
             +- OrderRepository.java
        这个目录结构是主流及推荐的做法，而在主入口类上加上 @SpringBootApplication 注解来开启 Spring Boot 的各项能力，如自动配置、组件扫描等

### 你如何理解 Spring Boot 中的 Starters？
    Starters可以理解为启动器，它包含了一系列可以集成到应用里面的依赖包，你可以一站式集成 Spring 及其他技术，而不需要到处找示例代码和依赖包。
    Starters分类
        Spring Boot应用类启动器
            启动器名称	                    功能描述
            spring-boot-starter	            包含自动配置、日志、YAML的支持。
            spring-boot-starter-web	        使用Spring MVC构建web 工程，包含restful，默认使用Tomcat容器。
        Spring Boot生产启动器
            启动器名称	                    功能描述
            spring-boot-starter-actuator	提供生产环境特性，能监控管理应用。
        Spring Boot技术类启动器
            启动器名称	                    功能描述
            spring-boot-starter-json	    提供对JSON的读写支持。
            spring-boot-starter-logging	    默认的日志启动器，默认使用Logback。
### 如何在 Spring Boot 启动的时候运行一些特定的代码？

    实现接口 ApplicationRunner 或者 CommandLineRunner
    这两个接口实现方式一样，它们都只提供了一个 run 方法
    启动的时候有多个ApplicationRunner和CommandLineRunner，想控制它们的启动顺序，
    可以实现 org.springframework.core.Ordered接口或者使用 org.springframework.core.annotation.Order注解。

### Spring Boot 有哪几种读取配置的方式？
    可以通过
    @PropertySource,
    @Value,
        @PropertySource+@Value注解读取方式

        @Component
        @PropertySource(value={"config/db-config.properties"})
        public class Pop {

            @Value("${db.name}")
            private String dbname;
        }
    @Environment,
        @Autowired
        private Environment env ;
        // 获取参数
        String getProperty(String key)

    @ConfigurationProperties
        @Component
        @ConfigurationProperties(prefix ="info")
        class Name {}

        @PropertySource+@ConfigurationProperties注解读取方式
        @Component
        @ConfigurationProperties(prefix="db")
        @PropertySource(value={"config/db-config.properties"})
        public class DBConfig2 {
    来绑定变量

### Spring Boot 支持哪些日志框架？推荐和默认的日志框架是哪个？
    支持 Java Util Logging, Log4j2, Lockback 作为日志框架，如果你使用 Starters 启动器，Spring Boot 将使用 Logback 作为默认日志框架
    既然默认自带了Logback框架，Logback也是最优秀的日志框架，往资源目录下创建一个logback-spring.xml即可，下面是一个参考配置文件。

   ![参考](https://mp.weixin.qq.com/s/OAyzUNIgBPkPVCy23gh-WA)

#### SpringBoot 实现热部署有哪几种方式？
    主要有两种方式：

    Spring Loaded
    Spring-boot-devtools

    引入 spring-boot-devtools
    自定义配置热部署
        # 热部署开关，false即不启用热部署
        spring.devtools.restart.enabled:true
        # 指定热部署的目录
        #spring.devtools.restart.additional-paths: src/main/java
        # 指定目录不更新
        spring.devtools.restart.exclude:test/**

        生产环境devtools将被禁用，如java -jar方式或者自定义的类加载器等都会识别为生产环境
        打包应用默认不会包含devtools，除非你禁用SpringBoot Maven插件的 excludeDevtools属性
        Thymeleaf无需配置 spring.thymeleaf.cache:false，devtools默认会自动设置，参考完整属性
        devtools会在windows资源管理器占用java进程，在开发工具里面杀不掉，只能手动kill掉，不然重启会选成端口重复绑定报错

### 你如何理解 Spring Boot 配置加载顺序？

    1）properties文件；
    2）YAML文件；
    3）系统环境变量；
    4）命令行参数；
    配置属性加载的顺序如下
    1、开发者工具 `Devtools` 全局配置参数；
    2、单元测试上的 `@TestPropertySource` 注解指定的参数；
    3、单元测试上的 `@SpringBootTest` 注解指定的参数；
    4、命令行指定的参数，如 `java -jar springboot.jar --name="Java技术栈"`；
    5、命令行中的 `SPRING_APPLICATION_JSONJSON` 指定参数, 如 `java -Dspring.application.json='{"name":"Java技术栈"}' -jar springboot.jar`
    6、`ServletConfig` 初始化参数；
    7、`ServletContext` 初始化参数；
    8、JNDI参数（如 `java:comp/env/spring.application.json`）；
    9、Java系统参数（来源：`System.getProperties()`）；
    10、操作系统环境变量参数；
    11、`RandomValuePropertySource` 随机数，仅匹配：`ramdom.*`；
    12、JAR包外面的配置文件参数（`application-{profile}.properties（YAML）`）
    13、JAR包里面的配置文件参数（`application-{profile}.properties（YAML）`）
    14、JAR包外面的配置文件参数（`application.properties（YAML）`）
    15、JAR包里面的配置文件参数（`application.properties（YAML）`）
    16、`@Configuration`配置文件上 `@PropertySource` 注解加载的参数；
    17、默认参数（通过 `SpringApplication.setDefaultProperties` 指定）；

    数字小的优先级越高，即数字小的会覆盖数字大的参数值


### Spring Boot 如何定义多套不同环境配置？
    applcation.properties

    application-dev.properties

    application-test.properties

    application-prod.properties

    spring.profiles.active=test
    也可以同时激活三个配置
    spring.profiles.active: prod,proddb,prodmq

    基于java代码激活
    在JAVA配置代码中也可以加不同Profile下定义不同的配置文件，@Profile注解只能组合使用@Configuration和@Component注解。

    指定Profile
        main：--spring.profiles.active=prod
    插件启动方式
        spring-boot:run -Drun.profiles=prod
    jar运行方式
        java -jar xx.jar --spring.profiles.active=prod
    SpringApplication.setAdditionalProfiles

### Spring Boot 可以兼容老 Spring 项目吗，如何做？
    可以兼容，使用 @ImportResource 注解导入老 Spring 项目配置文件。

### 保护 Spring Boot 应用有哪些方法？
    在生产中使用HTTPS
        要在Spring Boot应用程序中强制使用HTTPS，您可以扩展WebSecurityConfigurerAdapter并要求安全连接
        @Configuration
        public class WebSecurityConfigurerAdapter extends  WebSecurityConfigurerAdapter
         {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
         http.requiresChannel().requiresSecure(); }
        }
    使用Snyk检查你的依赖关系
    升级到最新版本
    启用CSRF保护
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    使用内容安全策略防止XSS攻击

   ![参考](https://mp.weixin.qq.com/s/HG4_StZyNCoWx02mUVCs1g)

### Spring Boot 2.X 有什么新特性？与 1.X 有什么区别？

    配置变更
    JDK 版本升级
    第三方类库升级
    响应式 Spring 编程支持
    HTTP/2 支持
    配置属性绑定
    更多改进与加强...

   ![参考](https://mp.weixin.qq.com/s/-WWBvWpD0Prib02XoU1sjw)


### 为什么我们需要 spring-boot-maven-plugin?
    提供了一些像 jar 一样打包或者运行应用程序的命令。

    spring-boot:run 运行你的 SpringBooty 应用程序。

    spring-boot：repackage 重新打包你的 jar 包或者是 war 包使其可执行

    spring-boot：start 和 spring-boot：stop 管理 Spring Boot 应用程序的生命周期（也可以说是为了集成测试）。

    spring-boot:build-info 生成执行器可以使用的构造信息
