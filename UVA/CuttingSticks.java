import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class CuttingSticks {
    //static BufferedReader br;
    static int[] cuts;
    static int[][] dp;

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);

        while(true){
            //int len = Integer.parseInt(br.readLine().trim());
            int len = sc.nextInt();
            if(len==0)break;
            //int n = Integer.parseInt(br.readLine().trim());
            int n = sc.nextInt();
            //int[] a = lineToIntArray(br.readLine().trim());

            cuts = new int[n+2];
            cuts[0] = 0;
            cuts[n+1] = len;

            for(int i=1; i<=n; i++){
                cuts[i] = sc.nextInt();
            }



            dp = new int[n+2][n+2];
            for (int[] row : dp) Arrays.fill(row, -1);


            System.out.printf("The minimum cutting is %d.\n", solve(0, n+2-1));

        }
    }

    public static int solve(int left, int right){


        if(dp[left][right]!=-1) return dp[left][right];
        if(left+1 >= right) return 0;


        int ans = Integer.MAX_VALUE;

        for(int i = left+1; i < right; i++){
            int temp = solve(left, i) + solve(i, right) + cuts[right] - cuts[left];

            if(ans>temp) ans = temp;

        }

        dp[left][right] = ans;

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

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }

}
