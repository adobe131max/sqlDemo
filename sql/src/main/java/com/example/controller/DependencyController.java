package com.example.controller;

import com.example.pojo.Dependency;
import com.example.pojo.Result;
import com.example.service.DependencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin    // 处理跨域
@RestController
@RequestMapping("/home")
public class DependencyController {

    @Autowired
    private DependencyService dependencyService;

    @GetMapping
    public Result list(String key, String value) {
        log.info("{}================={}",key, value);
        List<Dependency> list = dependencyService.list(key, value);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        Dependency dependency = dependencyService.selectById(id);
        return Result.success(dependency);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        dependencyService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/ids/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        dependencyService.deleteByIds(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Dependency dependency) {
        dependencyService.update(dependency);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Dependency dependency) {
        dependencyService.insert(dependency);
        return Result.success();
    }

    @GetMapping("/cal/{key}")
    public Result calculate(@PathVariable String key) {
        String ans = dependencyService.calculate(key);
        return Result.success(ans);
    }
}
