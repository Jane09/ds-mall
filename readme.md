# spring boot
    2.0.7

# spring cloud

## registry center
    consul
## ELK

# mall-auth
    认证鉴权相关的
## mall-auth-server
    客户端认证
        网关认证
    服务鉴权
        内部服务之间的访问鉴权
# mall-backend
    后台管理
## mall-backend-api
    后端接口
## mall-backend-ui
    vueh后台

# mall-control
    控制模块
    监控
    追踪

# mall-interface
    接口
    实体
    传输实体
# mall-uid
    uuid生成策略

# mall-api
    API网关
    spring-cloud-starter-gateway
    spring.cloud.gateway.enabled=false
    需要Netty运行时支持，springboot webflux
    It does not work in a traditional Servlet Container or built as a

## 术语
   路由：
   predicate：
   filter：
   运行：

   ![运行逻辑](./doc/spring_cloud_gateway_diagram.png)
   URIs defined in routes without a port will get a default port set to 80 and 443 for HTTP and HTTPS URIs respectively

    hystrix.command.fallbackcmd.execution.isolation.thread.timeoutInMilliseconds: 5000

   spring:
     cloud:
       gateway:
         routes:
         - id: after_route
           uri: http://example.org  #实际的请求地址
           predicates:
           - After=2017-01-20T17:42:47.789-07:00[America/Denver]
           - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
           - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
           - Cookie=chocolate, ch.p
           - Header=X-Request-Id, \d+ #匹配请求头参数，和一个正则表达式
           - Host=**.somehost.org     #匹配固定的主机
           - Method=GET
           - Path=/foo/{segment}
           - Query=baz  #a required param and an optional regexp
           - Query=foo, ba.
           - RemoteAddr=192.168.1.1/24  #可以自定义实现方式 RemoteAddressResolver
           filters:
           - AddRequestHeader=X-Request-Foo, Bar    #添加匹配的所有请求头参数
           - AddRequestParameter=foo, bar
           - AddResponseHeader=X-Response-Foo, Bar
           - Hystrix=myCommandName  #spring-cloud-starter-netflix-hystrix HystrixCommand注解的同名方法
           #
           - name: Hystrix
             args:
               name: fallbackcmd
               fallbackUri: forward:/incaseoffailureusethis #只支持forward
           - RewritePath=/consumingserviceendpoint, /backingserviceendpoint
           - PrefixPath=/mypath #添加前缀
           - PreserveHostHeader #无参数 确定是否应该发送原始主机标头，而不是由http客户机确定的主机标头
           #限流 redis限流 spring-boot-starter-data-redis-reactive，理论：https://en.wikipedia.org/wiki/Token_bucket
           - name: RequestRateLimiter
             args:
               redis-rate-limiter.replenishRate: 10 #期望每秒多少请求
               redis-rate-limiter.burstCapacity: 20 #容量 每秒最大的请求数
           # 也可以Bean定义
           - name: RequestRateLimiter
             args:
               rate-limiter: "#{@myRateLimiter}"
               key-resolver: "#{@userKeyResolver}"
           @Bean
           KeyResolver userKeyResolver() {
               return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
           }



