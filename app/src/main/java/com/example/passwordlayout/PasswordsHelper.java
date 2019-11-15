package com.example.passwordlayout;

import java.util.HashMap;
import java.util.Map;

public class PasswordsHelper {


    private final Map<Character, Character> russianToLatin = new HashMap<>();

    public PasswordsHelper(String[] russians, String[] latins) {
        if (russians.length != latins.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < russians.length; i++) {
            russianToLatin.put(russians[i].charAt(0), latins[i].charAt(0));
        }


    }

    public String convert(CharSequence source) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            char cLow = Character.toLowerCase(c);

            result.append(Character.isUpperCase(c) ?
                    Character.toUpperCase(russianToLatin.get(cLow)) : russianToLatin.get(cLow));

            if (result.length() <= i) {
                result.append(c);
            }
        }
        return result.toString();
    }
}
