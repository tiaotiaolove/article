package com.xiaobai.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页Controller
 * @author: bail
 * Time: 2018/1/25.15:16
 */
@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
