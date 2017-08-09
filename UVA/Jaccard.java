import java.io.*;
import java.util.*;
public class Jaccard {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);



        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; ++t){

            int[] tokens = lineToIntArray(br.readLine());
            int n=tokens[0];
            int m=tokens[1];

            HashSet<String> union = new HashSet<String>();
            HashSet<String> intersection = new HashSet<String>();

            String[] La = br.readLine().split(" ");
            String[] Lb = br.readLine().split(" ");

            for(int i=0; i<n; ++i){
                union.add(La[i]);
            }

            for(int i=0; i<m; ++i){

                if(union.contains(Lb[i])) intersection.add(Lb[i]);
                union.add(Lb[i]);

            }


            double AnB = intersection.size();
            double AuB = union.size();

            System.out.printf("%.6f\n", AnB/AuB );


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