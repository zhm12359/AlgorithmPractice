import java.io.*;
import java.util.*;
public class DiceThrowing {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        //pre compute
        long[][] dp = new long[25][150];


        for(int i=1; i<=6; ++i){
            dp[1][i] = 1;
        }
        for(int i=1; i<24; ++i){
            for(int j=i; j<=6*i; ++j){
                for(int k=1; k<=6; ++k){
                    dp[i+1][j+k] += dp[i][j];
                }
            }
        }


        while(true){

            int[] tks = lineToIntArray(br.readLine());
            int n=tks[0], x = tks[1];
            if(n==0&&x==0) break;

            //System.out.println(n+" " + x);


            if(n*6<x){
                System.out.println(0);
                continue;
            }
            if(x<=n){
                System.out.println(1);
                continue;
            }

            long allGreaterOutcome = 0;
            for(int i=x; i<= 6*n ; i++){
                allGreaterOutcome += dp[n][i];
            }

            long all = (long) Math.pow(6,n);
            //for(int i=0; i<n; i++) all = all*6;

            long gcd = gcd(all, allGreaterOutcome);

            System.out.printf("%d/%d\n", allGreaterOutcome/gcd, all/gcd);

        }

    }


    public static long gcd(long a, long b){
//        if(b==0) return a;
//        else return gcd(b,a%b);
        return b!=0 ? gcd(b, a % b) : a;
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