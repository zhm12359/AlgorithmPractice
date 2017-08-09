
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Anagram {
    public static int numberNeeded(String first, String second) {
        int ret = 0;
        TreeMap<String, Integer> m1 = new TreeMap();
        TreeMap<String, Integer> m2 = new TreeMap();

        for(char c: first.toCharArray()){
            String letter = c + "";
            if(m1.containsKey(letter)) m1.put(letter, m1.get(letter)+1);
            else m1.put(letter,1);
        }

        for(char c: second.toCharArray()){
            String letter = c + "";
            if(m2.containsKey(letter)) m2.put(letter, m2.get(letter)+1);
            else m2.put(letter,1);
        }

        for(String s : m1.keySet()){
            if(m2.containsKey(s)) ret += Math.abs(m1.get(s) - m2.get(s));
            else ret += m1.get(s);
            m2.put(s, 0);
        }
        for(String s: m2.keySet()){
            ret += m2.get(s);
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = "abcd";
        String b = "cdef";
        System.out.println(numberNeeded(a, b));
    }

}
