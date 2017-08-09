import java.io.*;
import java.util.*;
public class CruiseControl {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());


        for(int t=1; t<=T; t++){

            int[] tks = lineToIntArray(br.readLine());
            int D = tks[0];
            int n = tks[1];

            int[] position = new int[n];
            int[] speed = new int[n];

            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            double maxHour = Double.MIN_VALUE;



            for(int i=0; i<n; i++){
                tks = lineToIntArray(br.readLine());
                int pos = tks[0];
                int s = tks[1];

                if(min>s){
                    min = s;
                    minIndex = i;
                }
                position[i] = pos;
                speed[i] = s;

                double hour = (double) (D - pos) / (double) s ;

                if(maxHour < hour){
                    maxHour = hour;
                }

            }
//
//            int slowPos = position[minIndex];
//            double hoursToDest = (double) (D - slowPos) / (double) min ;

            System.out.printf("Case #%d: %.6f\n", t, D/maxHour);


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