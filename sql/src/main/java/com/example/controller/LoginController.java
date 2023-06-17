package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("员工登录：{}", user);
        User p = userService.login(user);
        if (p != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", p.getId());
            claims.put("username", p.getUsername());
            claims.put("password", p.getPassword());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("用户名或密码错误");
    }
}
