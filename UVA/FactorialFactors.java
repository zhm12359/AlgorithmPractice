import java.io.*;
import java.util.*;
public class FactorialFactors {

    static BufferedReader br;
    static int[] dp = new int[1000002];
    static int[] runningSum = new int[1000002];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);



        for(int n=2; n<1000002; n++){

            int nc =n;
            Map<Integer, Integer> factors = new TreeMap<>();

            for (int d = 2; d * d <= nc; d++) {
                if (nc % d == 0) {
                    int a = 0;
                    while (nc % d == 0) {
                        a++;
                        nc = nc/d;
                    }
                    factors.put(d, a);
                }
            }

            if (nc > 1) {
                factors.put(nc, 1);
            }

            for(int key : factors.keySet()){
                dp[n]+=factors.get(key);
            }
            runningSum[n]+= (dp[n]+runningSum[n-1]);

        }


        String line;
        while( (line=br.readLine())!=null){
            int n =Integer.parseInt(line);
//            int ans=0;
//            for(int i=0; i<n+1; i++){
//                ans+=dp[i];
//            }
            System.out.println(runningSum[n]);

//            System.out.println(n);
//            Map<Long, Integer> factors = new TreeMap<>();
//
//            for (long d = 2; d * d <= n; d++) {
//                if (n % d == 0) {
//                    int a = 0;
//                    while (n % d == 0) {
//                        a++;
//                        n = n/d;
//                    }
//                    factors.put(d, a);
//                }
//            }
//
//            if (n > 1) {
//                factors.put(n, 1);
//            }
//
//            System.out.println(factors);
//
//            int ans=0;
//            for(long key : factors.keySet()){
//                ans+=factors.get(key);
//            }
//            System.out.println("ans: "+ans);

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