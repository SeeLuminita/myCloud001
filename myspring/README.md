eureka项目为注册中心
order、order2为同一个客户端项目，为了模拟负载均衡，所以创建了两个项目
主要流程：user、order、order2同时注册到eureka上，user通过restTemplate+ribbon调用order服务，轮询调用order和order2
尚未写完的redis处理高并发，后续更新
