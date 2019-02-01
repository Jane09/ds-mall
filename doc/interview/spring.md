## spring框架
### spring bean初始化流程
    spring 5
#### 定义
    1、xml定义
    2、注解 @Controller、@Service @Repository
    3、定义configuration类，在里面@Bean
    4、编程定义
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        GenericBeanDefinition definition = (GenericBeanDefinition)BeanDefinitionBuilder
            .genericBeanDefinition(SomeService.class)
            .setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE)
            .getBeanDefinition();
        factory.registerBeanDefinition("someService", definition);
        factory.addBeanPostProcessor(new BeanPostProcessor(){
            @Override
              public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
              }
              @Override
              public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof ApplicationContextAware) {
                  GenericApplicationContext context = new GenericApplicationContext(factory);
                  ((ApplicationContextAware) bean).setApplicationContext(context);
                }
                return bean;
              }
        });
        // 再注册一个 bean post processor: AutowiredAnnotationBeanPostProcessor. 作用是搜索这个 bean 中的 @Autowired 注解, 生成注入依赖的信息.
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setBeanFactory(factory);
        factory.addBeanPostProcessor(autowiredAnnotationBeanPostProcessor);

        // getBean() 时, 初始化
        SomeService SomeService = factory.getBean("someService",SomeService.class);
        SomeService.doSomething();
#### 注册

#### 获取


### 事务管理总结
    配置方式
    1、每个Bean都有一个代理
    2、所有Bean共享一个代理基类
    3、使用拦截器
    4、tx标签拦截器
    5、全注解

    编程式事务
    声明式事务


### DispatcherServlet初始化工程

### 为什么要用spring

    new Object
    时间，内存
    工厂方法还是不够简洁
    第三方对象管理系统：IOC容器
    DI：依赖注入，IOC容器自动注入Bean到对象中供使用
    注入方式：自动注入、注解、xml配置

    组件扫描：
    自动装配：简洁，局限-引入第三方库呢？@Configuration + @Bean
    声明组件：



### 简述spring的结构

### IOC

    解耦，提供一种构造对象的方式；
    缺点：占用内存多、构造对象慢、启动慢


### AOP

    基于spring框架的切面编程企业级解决方案
    虽然这个已经很强大，但是依然还是没有aspectj强大
    比如：
        目前还不支持流程切面。
        织入慢执行效率低、
        不支持new关键字创建的对象的切入，
        必须使用bean factory织入。

### spring的生命周期

### BeanFactory和ApplicationContext

### Bean实例化的过程

### SpringMVC的工作原理

### springboot启动的机制

### 循环依赖的处理
    单例，多例状态下的处理、懒加载一定能处理掉循环依赖吗

### spring组件

### springcloud

