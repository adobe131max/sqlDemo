package com.example.service;

import com.example.pojo.Dependency;

import java.util.List;

public interface DependencyService {

    List<Dependency> list(String key, String value);

    Dependency selectById(Integer id);

    void deleteById(Integer id);

    void deleteByIds(List<Integer> ids);

    void update(Dependency dependency);

    void insert(Dependency dependency);

    String calculate(String key);
}
