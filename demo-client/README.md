# Demo Client

## RestTemplate
定义 RestTemplate
```java
@Bean
@LoadBalanced
RestTemplate restTemplate() {
    return new RestTemplate();
}
```

用 RestTemplate 调用远程接口
```java
restTemplate.getForObject("http://demo-service/?name=" + username, String.class);
```
注意 `demo-service` 可以大写，也可以小写

## Ribbon

## Feign

## Zuul