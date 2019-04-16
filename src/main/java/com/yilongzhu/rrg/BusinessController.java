package com.yilongzhu.rrg;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @RequestMapping("/rrg")
    public Business business() {
        return Generate.generateBusiness();
    }
}