1. eureka项目为注册中心服务端
2. order、order2为同一个客户端项目，为了模拟负载均衡，所以创建了两个项目
3. 主要功能：user、order、order2同时注册到eureka上，user通过restTemplate+ribbon负载均衡调用order，轮询访问order和order2
4. 尚未写完的redis处理高并发，后续更新
