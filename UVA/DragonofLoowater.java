import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class DragonofLoowater {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int Cases = Integer.parseInt(br.readLine());

        while(true){

            int[] tokens = lineToIntArray(br.readLine());
            int n = tokens[0];
            int m = tokens[1];

            if(n==0 && m==0) break;

            int[] heads= new int[n];
            int[] knights = new int[m];


            for(int i=0; i<n; ++i){
                heads[i] = Integer.parseInt(br.readLine());
            }

            for(int i=0; i<m; ++i){
                knights[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(heads);
            Arrays.sort(knights);

            int cost=0;

            int hd=0;

            for(int k=0; k<m; ++k){

                if(hd==n) break;

                if(knights[k]>=heads[hd]){
                    cost += knights[k];
                    ++hd;
                }

            }

            if(hd==n) System.out.println(cost);
            else System.out.println("Loowater is doomed!");



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
