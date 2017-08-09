import java.io.*;
import java.util.*;
public class CoinChange {

    static BufferedReader br;

    static int[] coins = {5,10,25,50};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        //String line = br.readLine();
//        while(line!=null){
//
//            int n = Integer.parseInt(line);
////            if(res[n]==1){
////                pw.printf("There is only 1 way to produce %d cents change.\n", n);
////            }else{
////                pw.printf("There are %d ways to produce %d cents change.\n", res[n], n);
////            }
//
//            line=br.readLine();
//
//        }
        System.out.println("Happening?");

        System.out.println(ways(11));
    }

    public static long ways(int n){

        if(n<=0) return 0;
        if(n<5) return 1;
        if(n==5) return 2;

        long ans = 1; //use all ones
        for(int i=0; i<4; ++i){

            System.out.printf("n %d   coins[i] %d\n", n, coins[i]);
            if(ways(n-coins[i])>0) {


                System.out.println("here"+(n-coins[i]));
                ans++;
            }

        }

        return ans;

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
