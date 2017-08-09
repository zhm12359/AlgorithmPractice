import java.io.*;
import java.util.*;
public class WinningStreak {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        while(true){
            int n = sc.nextInt();
            if(n==0) break;
            double p = sc.nextDouble();

            double[][] f= new double[n+1][n+1];  //i by j, where i is num of games, and j is winning streak less or equal to j



            for(int i=0; i<=n; i++){
                for(int j=0; j<=n; j++){

                    if(j>=i) f[i][j] = 1; //the probability at i whose winning streak is less than i is definitely 1

                    if(j == i-1 ) f[i][j] = 1 - Math.pow(p, i);
                    if(i-1>j){
                        f[i][j] = f[i-1][j] - f[i-j-2][j]*(1-p)*Math.pow(p,j+1);
                    }
                }
            }

            double ans = 0.0;
            for (int j = 1; j <= n; ++j) {
                ans += j * (f[n][j] - f[n][j - 1]);
            }
            System.out.printf("%.6f\n",ans);


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