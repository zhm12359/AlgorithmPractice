import java.io.*;
import java.util.*;
public class Antimonotonicity {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int[] array = lineToIntArray(br.readLine());
            int N = array[0];
            boolean testBiggerThan = true;

            int counter = 0;
            int cur = Integer.MIN_VALUE;
            int prev = Integer.MIN_VALUE;


            for(int i=1; i<=N; ++i){
                cur = array[i];
                if(testBiggerThan){

                    if(cur > prev){
                        counter++;
                        testBiggerThan = false;
                        prev = cur;
                    }else if(cur < prev){
                        prev = cur; // set prev as small as possible
                    }

                }else {

                    if (cur < prev) {
                        counter++;
                        testBiggerThan = true;
                        prev = cur;
                    } else if (cur > prev) {
                        prev = cur; // set prev as large as possible
                    }

                }

            }

            pw.println(counter);
            pw.flush();



        }


    }

    public static int[] lineToIntArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }


}