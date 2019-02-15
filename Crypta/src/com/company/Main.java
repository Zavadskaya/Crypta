package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main{

    public static void main(String[] args) {
        getKey();

    }

    public static  void getKey() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the word :");
        String word = in.nextLine();
        in.close();
        List<Character> result = word.toUpperCase().chars().mapToObj(c->(char)c).collect(Collectors.toList());
        System.out.println(result);

        /*for(int i=0;i<result.size();i++)
        {
            System.out.println(result.get(i) + "=" + i);
        }*/

        List<Character> alphabet = IntStream.rangeClosed('A', 'Z')
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        List<Character> key = IntStream.rangeClosed('A', 'Z')
                .mapToObj(a -> (char) a)
                .collect(Collectors.toList());

        System.out.println(alphabet);
        Collections.shuffle(key);
        System.out.println(key);

        Map<Character, Character> map = IntStream.range(0, alphabet.size())
                .boxed()
                .collect(Collectors.toMap(i -> alphabet.get(i), i -> key.get(i)));
        System.out.println(map);

        List<Character>subset = new ArrayList<>();
        for(Character val : result)
        {
            if(map.containsKey(val.charValue()))
            {
                subset.add(map.get(val));
            }
        }
        System.out.println(subset);
        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("file.txt"),"UTF-8"));
            writer.write(String.valueOf(alphabet));
            writer.write(String.valueOf(key));

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            try {
                writer.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }

    }


}
