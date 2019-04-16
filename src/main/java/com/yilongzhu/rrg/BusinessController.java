package com.yilongzhu.rrg;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @RequestMapping("/rrg")
    public Business business(@RequestParam double latitude,
                             @RequestParam double longitude,
                             @RequestParam int radius) {
        return Generate.generateBusiness(latitude, longitude, radius);
    }
}