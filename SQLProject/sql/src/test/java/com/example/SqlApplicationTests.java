package com.example;

import com.example.utils.Calculator;
import com.example.utils.StringPro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SqlApplicationTests {

    @Autowired
    private Calculator calculator;

    @Autowired
    private StringPro stringPro;

    @Test
    void contextLoads() {
    }

    @Test
    public void testString() {
        System.out.println(stringPro.addPercentBetweenChars("ABC"));
    }

    @Test
    public void testCalculator() {
        calculator.calculate("A");
    }

}
