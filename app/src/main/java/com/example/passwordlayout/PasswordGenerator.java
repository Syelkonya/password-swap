package com.example.passwordlayout;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordGenerator {

    private boolean checkUppercase;
    private boolean addNumbers;
    private boolean addSpecialSymbols;
    private int length;

    private final char[] arrayOfSymbols = { '~', '`', '!', '@', '#',
            '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', '{',
            ']', '}', '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>', '/',
            '?'};

    private final String numbers = "0123456789";
    private final String  alphabet= "abcdefghijklmnopqrstuvwxyz";

    public PasswordGenerator(){

    }

    public String generate(boolean checkUppercase, boolean addNumbers,
                           boolean addSpecialSymbols, int length){
        StringBuilder sb = new StringBuilder();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i <= length;) {
            if (checkUppercase && (Math.random() > 0.5)){
                list.add(Character.toUpperCase(generateLetter()));
                i++;
            }
            if (addNumbers && (Math.random() > 0.5)){
                list.add(generateNumber());
                i++;
            }
            if (addSpecialSymbols && (Math.random() > 0.5)){
                list.add(generateSymbol());
                i++;
            }
            if (Math.random() > 0.5){
                list.add(generateLetter());
                i++;
            }
        }

        for (int i = 0; i < length ; i++) {
            sb.append(list.get(i));
        }


        return sb.toString()
                .replace(",","")
                .replace(" ","")
                .replace("[", "")
                .replace("]","");
    }

    public char generateSymbol(){
        return arrayOfSymbols[(int) (Math.random()* arrayOfSymbols.length)];
    }

    public char generateNumber(){
        return numbers.charAt((int)(Math.random()*9));
    }

    public char generateLetter(){
        return alphabet.charAt((int)(Math.random()*alphabet.length()));
    }
}
