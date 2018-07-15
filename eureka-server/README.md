# 服务发现、注册中心 Eureka Server

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

```java
@EnableEurekaServer
```

bootstrap.properties
```properties
spring.application.name=eureka-server
spring.cloud.config.uri=http://localhost:8888
```

在配置服务仓库中创建 `eureka-server.properties`
```properties
foo=bar
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.server.enable-self-preservation=false
eureka.instance.prefer-ip-address=true
server.port=${PORT:8761}
#server.port=8761
```