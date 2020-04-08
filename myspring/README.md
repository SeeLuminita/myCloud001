1. eureka项目为注册中心服务端
2. order、order-rest-exec.jar（后续称之为order2）为同一个项目,端口不同（order-rest-exec.jar下载下来使用java -jar命令启动），为了模拟负载均衡，所以创建了两个项目
3. 主要功能：user、order、order2同时注册到eureka上，user通过restTemplate+ribbon和feign两种方式(负载均衡)调用order，轮询访问order和order2
4. 尚未写完的redis处理高并发，后续更新

**2020-04-03 09:00调整**
1. 在user项目中添加了feign调用方式

**2020-04-08总结**
1. feign熔断回调有问题  
   踩坑点:    
   1. 确定测试的接口间的调用是通过feign调用实现的
   2. 服务端的fallback指定类上要加上@Component注解
   3. 客户端启动类上的@SpringBootApplication注解的扫描包路径要加上fallback那个类所在包地址，加上后出现了扫不到客户端启动类子包下的bean，要将子包下的路径也添进去
   4. 客户端启动类上有多个可以扫描包的注解，作用不同，譬如@EnableFeignClients扫描feignClient的bean、@ComponentScan、@SpringBootApplication扫描所有的spring的bean等，当前版本2.1.13@SpringBootApplication已包含@ComponentScan