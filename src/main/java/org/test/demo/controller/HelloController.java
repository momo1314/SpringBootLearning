package org.test.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.test.demo.data.domain.Test;

import java.util.Map;

@Controller
public class HelloController {

    //自定义属性注册
    @Value("${org.test.all}")
    public String all;

    //获取自定义属性bean
    @Autowired
    Test test;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String index(Map<String, Object> map) {
        map.put("name" , this.all);
        return "index";
    }


}
