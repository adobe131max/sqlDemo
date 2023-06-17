package com.example.utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StringPro {

    public String addPercentBetweenChars(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append(input.charAt(i));
            if (i != input.length() - 1) {
                output.append("%");
            }
        }
        return output.toString();
    }

    public String sortString(String input) {
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
