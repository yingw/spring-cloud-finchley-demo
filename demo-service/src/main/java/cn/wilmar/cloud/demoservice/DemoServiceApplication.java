package cn.wilmar.cloud.demoservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class DemoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }
}

@RestController
class HelloService {

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloService(@RequestParam(value = "name", defaultValue = "") String name) {
        return "Hello, Im " + name + ", and I come from " + port;
    }
}