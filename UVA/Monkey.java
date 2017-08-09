import java.io.*;
import java.util.*;
public class Monkey {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int N = Integer.parseInt(br.readLine());

            int[] ladder = lineToIntArray(br.readLine());

            int l=0;
            int r=Integer.MAX_VALUE;
            int ans = Integer.MAX_VALUE;

            if(N>1){
                while(l<=r){
                    int mid = (l+r)/2;
                    //System.out.println(mid);
                    if(fit(mid, ladder)){
                        r = mid - 1;
                        ans = ans > mid ? mid : ans;
                    }else{
                        l = mid + 1;
                    }
                }
            }else if(N==1){
                ans = ladder[0];
            }else if(N==0){
                ans = 0;
            }



            pw.printf("Case %d: %d\n", (t+1), ans);
            pw.flush();

        }

    }

    public static boolean fit(int k, int[] ladder){

        int strength = k;
        int prev = 0;

        for(int i=0; i<ladder.length; ++i){

            int cur = ladder[i];
            if(cur - prev > strength) return false;
            else if(cur - prev == strength) strength = strength -1;
            prev = cur;
        }

        if(strength>=0) return true;
        else return false;

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