package cn.wilmar.cloud.democlient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class HelloController {

    @Autowired RestTemplate restTemplate;
    @Value("${client.user.name}")
    private String username;

//    @Autowired
//    public HelloController(@Value("${client.user.name}") String username) {
//        System.out.println("HelloController.HelloController");
//        System.out.println("username = " + username);
//        this.username = username;
//    }

    @GetMapping("/")
    public String hello() {
        return restTemplate.getForObject("http://demo-service/?name=" + username, String.class);
    }
}
