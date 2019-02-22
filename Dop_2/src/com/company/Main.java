package com.company;


import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("DOP TASK 2");
        Scanner ins = new Scanner(System.in);
        System.out.println("Enter the first key:");
        int first = ins.nextInt();


        Scanner ini = new Scanner(System.in);
        System.out.println("Enter the second key :");
        int second = ini.nextInt();

        String cipher = encryptSec(getTextDop(), first, second);
        String deciphered = decryptSec(cipher,first,second);
        System.out.println("Encrypted: " + cipher.toUpperCase());
        System.out.println("Decrypted: " + deciphered.toUpperCase());
        ins.close();
        ini.close();
    }

    final static int module = 26;


    public static String getTextDop() {
        //List<Character> result = null;
        String content = null;
        try {
            File file = new File("ct4.txt");
            FileReader fr = new FileReader(file);
            char[] a = new char[200];
            fr.read(a);
            content = new String(Files.readAllBytes(Paths.get("ct4.txt")), "UTF-8");
            System.out.println("Original text: ");
            //result = content.toUpperCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            System.out.println(content);
            fr.close();
        } catch (Exception ex) {
            ex.getMessage();

        }

        return content.toLowerCase();
    }

    public static String encryptSec(String input, int first, int second) {
        StringBuilder builder = new StringBuilder();

        try {

            for (int in = 0; in < input.length(); in++) {
                char character = input.charAt(in);
                if (Character.isLetter(character)) {
                    character = (char) ((first * (character - 'a') + second) % module + 'a');
                }
                builder.append(character);
            }

        } catch (Exception ex) {
            ex.getMessage();
        }
        return builder.toString();
    }


    static String decryptSec(String input, int first, int second) {
        StringBuilder builder = new StringBuilder();
        BigInteger inverse = BigInteger.valueOf(first).modInverse(BigInteger.valueOf(module));
        for (int in = 0; in < input.length(); in++) {
            char character = input.charAt(in);
            if (Character.isLetter(character)) {
                int decoded = inverse.intValue() * (character - 'a' - second + module);
                character = (char) (decoded % module + 'a');
            }
            builder.append(character);
        }
        return builder.toString();
    }
}


