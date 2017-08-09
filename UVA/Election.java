import java.io.*;
import java.util.*;
public class Election {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int N = Integer.parseInt(br.readLine());
            int[] votes = lineToIntArray(br.readLine());

            int[] others = new int[N-1];
                    //Arrays.copyOfRange(votes, 0, votes.length-1);

            int sum=votes[0];
            for(int i=1; i<N; ++i){
                others[i-1] = votes[i];
                sum += votes[i];
            }

            int john = votes[0];
            int l=0;
            int r=Integer.MAX_VALUE;
            int mid = 0;
            int ans = Integer.MAX_VALUE;

            //System.out.printf("sum: %d\n", sum);


            while(l<=r){
                mid = (l+r)/2;
                //System.out.printf("mid: %d\n", mid);
                if(fit(others, mid, john )){
                    r = mid -1;
                    ans = ans > mid ? mid : ans;
                }else{
                    l = mid +1;
                }
            }

            System.out.println(ans);


        }

    }

    public static boolean fit(int[] others, int ans, int john){

        int johnWillHave = john+ans;
        int threshHold = johnWillHave;

        int left = ans;

        for(int i=0; i<others.length; ++i){

            int c = others[i];

            if(c>=threshHold){
                int needToReduce = c - threshHold + 1;
                left -= needToReduce;
            }

            if(left<0) return false;

        }

        return true;


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
