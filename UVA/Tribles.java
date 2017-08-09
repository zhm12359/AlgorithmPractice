import java.io.*;
import java.util.*;

// f(i) = P0 + P1 * f(i-1) + P2 * f(i-1)^2 + ... + Pn * f(i-1)^n
// where i is num of days, f(i) is the probability at i day that everything dies for a starting 1 trible
public class Tribles {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t =1; t<=T; ++t){

//            int[] tks = lineToIntArray(br.readLine().trim());
//            int n=tks[0], k=tks[1], m=tks[2];
            int n = sc.nextInt();
            int k = sc.nextInt();
            int m = sc.nextInt();
            double[] p = new double[n];
            for(int i=0; i<n; i++){
                //p[i] = Double.parseDouble(br.readLine().trim());
                p[i] = sc.nextDouble();
            }

            double[] f = new double[m+1];
//            f[0] = 0;
//            f[1] = p[0];
//            for(int i=1; i<=m; i++){
//                f[i]=0.0;
//                for(int j=0; j<n; j++){
//                    f[i] += p[j]*Math.pow(f[i-1], j);
//                }
//
//            }

            for (int i = 1; i <= m; i++){
                for (int j = 0; j < n; j++){
                    f[i] += p[j]*Math.pow(f[i-1], j);
                }
            }



            double ans = Math.pow(f[m], k);

            System.out.printf("Case #%d: %.7f\n",t, ans);

        }
        sc.close();
    }


}