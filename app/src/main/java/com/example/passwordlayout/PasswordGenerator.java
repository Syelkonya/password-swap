package com.example.passwordlayout;


import java.util.HashSet;
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
        Set<Character> set = new HashSet<>();
        for (int i = 0; i <= length;) {
            if (checkUppercase && (Math.random() > 0.5)){
                set.add(Character.toUpperCase(generateLetter()));
                i++;
            }
            if (addNumbers && (Math.random() > 0.5)){
                set.add(generateNumber());
                i++;
            }
            if (addSpecialSymbols && (Math.random() > 0.5)){
                set.add(generateSymbol());
                i++;
            }
            if (Math.random() > 0.5){
                set.add(generateLetter());
                i++;
            }
        }


        return set.toString()
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
