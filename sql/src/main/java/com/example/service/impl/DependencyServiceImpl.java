package com.example.service.impl;

import com.example.mapper.DependencyMapper;
import com.example.pojo.Dependency;
import com.example.service.DependencyService;
import com.example.utils.Calculator;
import com.example.utils.StringPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependencyServiceImpl implements DependencyService {

    @Autowired
    private DependencyMapper dependencyMapper;

    @Autowired
    private Calculator calculator;

    @Autowired
    private StringPro stringPro;

    @Override
    public List<Dependency> list(String key, String value) {
        if(key!=null && !key.equals("")){
            key = stringPro.addPercentBetweenChars(stringPro.sortString(key));
        }
        if(value!=null && !value.equals("")){
            value = stringPro.addPercentBetweenChars(stringPro.sortString(value));
        }
        return dependencyMapper.list(key, value);
    }

    @Override
    public Dependency selectById(Integer id) {
        return dependencyMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        dependencyMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        dependencyMapper.deleteByIds(ids);
    }

    @Override
    public void update(Dependency dependency) {
        dependency.setKey(stringPro.sortString(dependency.getKey()));
        dependency.setValue(stringPro.sortString(dependency.getValue()));
        dependencyMapper.update(dependency);
    }

    @Override
    public void insert(Dependency dependency) {
        dependency.setKey(stringPro.sortString(dependency.getKey()));
        dependency.setValue(stringPro.sortString(dependency.getValue()));
        dependencyMapper.insert(dependency);
    }

    @Override
    public String calculate(String key) {
        return calculator.calculate(key);
    }
}
