import java.io.*;
import java.util.*;
public class BathroomStall2 {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<T+1; t++){
            long[] tks = lineToLongArray(br.readLine());
            long n = tks[0];
            long k = tks[1];
            long max =0;
            long min = 0;

            int level = (int) Math.ceil(Math.log(k)/Math.log(2))+1;
            //System.out.println("level "+level);

            long ans = n;
            int count = 0;

            while(count<level){
                count++;

                if(ans%2!=0){
                    ans =  ans/ 2 ;
                    max = ans;
                    min = ans;
                }else{
                    min =  (ans-1)/ 2 ;
                    max = ans/ 2 ;
                    ans =  ans/ 2 ;

                }


            }

            /*

            if(n%2!=0) {
                long neighbor = (long) Math.floor( n/ (0x1 << level) );
                max=neighbor;
                min=neighbor;
            }else{
                min = (long) Math.floor( (n-1)/ (0x1 << level) );
                max = (long) Math.floor( n/ (0x1 << level) );
            }
            */



            System.out.printf("Case #%d: %d %d\n", t, max, min);

        }
    }

    public static long[] lineToLongArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        long[] res = new long[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }

}