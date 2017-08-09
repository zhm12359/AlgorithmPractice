import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Scanner;

public class Palindromes {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String word,sub;
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        int size;


        while(sc.hasNext()){
            hmap.clear();
            word = sc.next();
            size = word.length();

            for(int i=0;i<size; ++i){
                for(int j=i+1; j<size+1; j++){
                    sub = word.substring(i,j);
                    if(isPalidrome(sub)) {
                        if(!hmap.containsKey(sub)) hmap.put(sub,1);
                    }
                }
            }

            System.out.printf("The string '%s' contains %d palindromes.\n", word,hmap.size() );
        }


    }

    public static boolean isPalidrome(String word){

        if(word.length()==0) return false;

        String[] wordArray = word.split("");
        int left = 0;
        int right = wordArray.length-1;

        while(left<=right){
            if(!wordArray[left].equals(wordArray[right])) return false;
            right --;
            left ++;
        }

        return true;


    }
}
