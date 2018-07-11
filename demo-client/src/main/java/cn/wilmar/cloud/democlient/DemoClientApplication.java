package cn.wilmar.cloud.democlient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoClientApplication.class, args);
    }

    @Bean @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class HelloController {

    private static final String username = "Yin Guo Wei";
    @Autowired RestTemplate restTemplate;

    @GetMapping("/")
    public String hello() {
        return restTemplate.getForObject("http://demo-service/?name=" + username, String.class);
    }
}
