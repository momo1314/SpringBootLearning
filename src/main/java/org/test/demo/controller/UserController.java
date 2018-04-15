package org.test.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.test.demo.data.domain.User;
import org.test.demo.data.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;

    @Autowired //默认装配
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 注册并保存session
     * @param user
     * @param session
     * @return
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Map> register(@RequestBody User user , HttpSession session  ) {
        if(userService.findByName(user.getUsername()) != null) {
            Map<String, String> result = new HashMap<>();
            result.put("data", "toLogin");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        userService.add(user);
        Map<String , String> map = new HashMap<>();
        map.put("data" , "success");
        session.setAttribute("user",user);
        return new ResponseEntity<Map>(map,HttpStatus.OK);

    }


    /**
     * 登录并保存session
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ResponseEntity<Map> login(@RequestBody User user , HttpSession session) {
        User temp = userService.findByName(user.getUsername());
        if(temp!= null) {
            if(userService.comparePassword(user,temp)) {
                Map<String , String> map = new HashMap<>();
                map.put("data" , "success");
                session.setAttribute("user",temp);
                return new ResponseEntity<Map>(map,HttpStatus.OK);
            }
            Map<String , String> map = new HashMap<>();
            map.put("data" , "warning password");
            return new ResponseEntity<Map>(map,HttpStatus.OK);
        }
        Map<String , String> map = new HashMap<>();
        map.put("data" , "please toRegister");
        return new ResponseEntity<Map>(map,HttpStatus.OK);

    }
}
