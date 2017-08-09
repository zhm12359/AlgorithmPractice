import java.io.*;
import java.util.*;
public class Coins {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        //precompute
        long[] res = new long[30001];

        int[] coins = {1,5,10,25,50};

        res[0] =1;

        for(int i=0; i<5; ++i){
            for(int j=coins[i]; j<30001; ++j){
                res[j] += res[j-coins[i]];
            }
        }

        String line = br.readLine();
        while(line!=null){

            int n = Integer.parseInt(line);
            if(res[n]==1){
                pw.printf("There is only 1 way to produce %d cents change.\n", n);
            }else{
                pw.printf("There are %d ways to produce %d cents change.\n", res[n], n);
            }

            line=br.readLine();

        }

        pw.flush();



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