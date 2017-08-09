import java.io.*;
import java.util.*;
public class FerryLoadingII2 {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int Ta = Integer.parseInt(br.readLine());

        for(int cases=0; cases<Ta; ++cases ){

            int[] tokens = lineToIntArray(br.readLine());
            int n = tokens[0]; //capacity
            int t = tokens[1]; //time for single trip
            int m = tokens[2]; // total number of cars

            int[] T = new int[m];

            for(int i=0; i<m; ++i){
                T[i] = Integer.parseInt(br.readLine());
            }

            int ans=0;
            int pos=0;

            if(m%n==0){
                ans=T[n-1]+2*t;
                pos=n;
            }else{
                ans=T[m%n-1]+2*t;
                pos=m%n;
                System.out.println(T[m%n-1]);
            }
            System.out.println(ans);

            for(;pos<m;pos+=n){
                if(ans>=T[pos+n-1]) ans=ans+2*t;
                else ans=T[pos+n-1]+2*t;
                System.out.println(ans);
            }

            ans-=t;

            pw.printf("%d %d\n", ans, (m+n-1/n));
            pw.flush();


        }

        //greedy thoughts: it's guarenteed that time is the shortest
        // if we reach max capacity first and then ship out



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