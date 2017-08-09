import java.io.*;
import java.util.*;
public class E {

    static BufferedReader br;
    static int X;
    static int Y;



    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){
            int[] tks = lineToIntArray(br.readLine());
            int N = tks[0];
            X = tks[1];
            Y = tks[2];

            int[] a = lineToIntArray(br.readLine());
            int[] b = lineToIntArray(br.readLine());

            Arrays.sort(a);
            Arrays.sort(b);

            int l=0;
            int r=N;
            int mid = 0;
            int ans = Integer.MIN_VALUE;

            //System.out.printf("sum: %d\n", sum);


            while(l<=r){
                mid = (l+r)/2;
                //System.out.printf("mid: %d\n", mid);
                if(fit(mid, a, b )){
                    l = mid + 1;
                    ans = ans > mid ? mid : ans;
                }else{
                    r = mid -1;
                }
            }

            System.out.println(ans);



        }
    }

    public static boolean fit(int ans, int[] a, int[] b){

        int i=0;
        int j=0;

        int found=0;
        while(i<a.length&& j<b.length){

            int ca = a[i];
            int cb = b[j];

            if(ca+cb>Y) break;
            if(Math.abs(ca-cb)<=X) found++;
            i++;
            j++;
        }

        if(found>=ans) return true;
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
