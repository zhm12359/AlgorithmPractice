import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class AggressiveCows {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //binary search
        for(int t=0; t<T; ++t){

            int[] tokens = lineToIntArray(br.readLine());
            int N = tokens[0];
            int C = tokens[1];

            int[] pos = new int[N];
            for(int i=0; i<N; ++i){
                pos[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(pos);
            int l=0;
            int r=Integer.MAX_VALUE;
            int ans = Integer.MIN_VALUE;
            //System.out.println(Arrays.toString(pos));

            while(l<=r){
                int mid = (l+r) /2;
                if(fit(mid, pos, C)){
                    l = mid +1;
                    ans = Math.max(mid, ans);
                }else{
                    r = mid -1;
                }
            }


            System.out.println(ans);

        }
    }



    public static boolean fit(int ans, int[] pos, int cows){

        //System.out.println("testing "+ans);

        int prev=pos[0];
        int num=1;
        for(int i=1; i<pos.length; ++i){
            int cur = pos[i];
            if(cur-prev>=ans) {
                num++;
                prev=cur;
            }
            if(num==cows) return true;
        }

        if(num==cows) return true;
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