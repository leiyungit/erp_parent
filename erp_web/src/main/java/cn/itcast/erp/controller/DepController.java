package cn.itcast.erp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/dep")
public class DepController {

    @RequestMapping("/test")
    public String test(){
        return "test: "+new Random().nextInt(100);
    }
}
