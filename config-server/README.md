# 配置中心 Config Server

http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_spring_cloud_config

## config-server

依赖 pom.xml
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

启用 ConfigServer
```java
@EnableConfigServer
```

配置项 `application.properties`
```properties
server.port=8888
spring.profiles.active=native
spring.cloud.config.server.native.search-locations=file:///D:/Projects/spring-cloud-finchley-demo/config-repo
```
或者
```properties
server.port=8888
spring.cloud.config.server.git.uri=https://github.com/yingw/spring-cloud-finchley-demo
spring.cloud.config.server.git.search-paths=config-repo
```

### 仓库 location 的配置方式

有多个方式来配置仓库位置：
- 本地仓库
- Git 仓库
- SVN 仓库等

使用本地仓库方式一定要设置 `spring.profiles.active=native`

Git 仓库的配置方式：
```properties
spring.cloud.config.server.git.uri=${HOME}/bootiful-microservices-config
spring.cloud.config.server.git.uri=https://git.oschina.net/yinguowei/cloud-config-repo.git
```

本地仓库的例子：
```properties
spring.cloud.config.server.git.uri=file:///C:/Users/yingu/cloud-config-repo
spring.cloud.config.server.git.uri=file://${user.home}/cloud-config-repo 
```
注意 Windows 下需要用三个 / 来设定盘符路径。 `$[HOME]` `${user.home}`  是 Mac、Linux 下环境变量

读取配置的多个格式：
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```

例如

- http://localhost:8888/eureka-server/default
- http://localhost:8888/eureka-server/default/master
- http://localhost:8888/eureka-server-default.yml
- http://localhost:8888/master/eureka-server-default.properties

### 多 profile 配置

TBD

## 客户端使用

依赖
```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

配置，要用文件名 bootstrap.properties
```properties
spring.application.name=demo-service
spring.cloud.config.uri=http://localhost:8888
```

在配置仓库中新建个和应用名，配置 `spring.application.name` 相同的配置文件，如 demo-service.properties，再把一些由配置中心集中配置的属性（如端口）放到配置中心去
```properties
server.port=${PORT:8000}
```

### 刷新配置
由于 2.0 开始没有开放所有管理端点，还需要开发 /actuator/refresh
```properties
management.endpoints.web.exposure.include=*
```
或者
```properties
management.endpoints.web.exposure.include=health, info, refresh
```

在设置可刷新配置的类上声明
```java
@RefreshScope
```

测试提交
```
curl -X POST -d {} -H "Content-Type: application/json" http://localhost:9999/actuator/refresh
```