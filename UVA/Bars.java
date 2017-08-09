import java.io.*;
import java.util.*;
public class Bars {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){
            int n=Integer.parseInt(br.readLine());
            int m=Integer.parseInt(br.readLine());

            int[] arr = lineToIntArray(br.readLine());

            boolean can=false;
            for (int j = 0; j < (1 << m); j++) {
                int sum=0;
                for (int k = 0; k < m; k++) {
                    if ((j & (1 << k)) != 0) { //use kth element in this mask j
                        sum+=arr[k];
                    }
                }

                if(sum==n) {
                    can=true;
                    break;
                }

            }

            pw.println(can?"YES":"NO");
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