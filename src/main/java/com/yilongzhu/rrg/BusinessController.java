package com.yilongzhu.rrg;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BusinessController {

    @CrossOrigin
    @RequestMapping("/rrg")
    public Business business(@RequestParam Map<String, String> parameters) {
        return Generate.generateBusiness(parameters);
    }
}