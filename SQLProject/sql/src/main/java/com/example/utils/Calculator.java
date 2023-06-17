package com.example.utils;

import com.example.mapper.DependencyMapper;
import com.example.pojo.Dependency;
import com.example.pojo.FuncDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Calculator {

    @Autowired
    DependencyMapper dependencyMapper;

    @Autowired
    StringPro stringPro;

    public String calculate(String key) {
        List<Dependency> list = dependencyMapper.list(null, null);
        Set<Character> keys = new HashSet<>();
        Set<FuncDependency> funcDependencies = new HashSet<>();
        key.chars().forEach(c -> keys.add((char) c));
        list.forEach(dependency -> {
            FuncDependency funcDependency = new FuncDependency();
            Set<Character> deter = new HashSet<>();
            Set<Character> depen = new HashSet<>();
            dependency.getKey().chars().forEach(c -> deter.add((char) c));
            dependency.getValue().chars().forEach(c -> depen.add((char) c));
            funcDependency.setDeterminant(deter);
            funcDependency.setDependent(depen);
            funcDependencies.add(funcDependency);
        });
        return attributeClosure(funcDependencies, keys);
    }

    public String attributeClosure(Set<FuncDependency> funcDependencies, Set<Character> keys) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (FuncDependency funcDependency : funcDependencies) {
                Set<Character> deter = funcDependency.getDeterminant();
                Set<Character> depen = funcDependency.getDependent();
                if (keys.containsAll(deter) && !keys.containsAll(depen)) {
                    keys.addAll(depen);
                    changed = true;
                }
            }
        }
        StringBuilder s = new StringBuilder();
        keys.forEach(s::append);
        String res = s.toString();
        return stringPro.sortString(res);
    }
}
