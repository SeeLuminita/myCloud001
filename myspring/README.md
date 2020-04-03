1. eureka项目为注册中心服务端
2. order、order-rest-exec.jar（后续称之为order2）为同一个客户端项目,端口不同（order-rest-exec.jar下载下来使用java -jar命令启动），为了模拟负载均衡，所以创建了两个项目
3. 主要功能：user、order、order2同时注册到eureka上，user通过restTemplate+ribbon负载均衡调用order，轮询访问order和order2
4. 尚未写完的redis处理高并发，后续更新

**2020-04-03 09:00调整**
1. 在user项目中添加了feign调用方式