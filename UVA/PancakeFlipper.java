import java.io.*;
import java.util.*;
public class PancakeFlipper {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            String[] tks = br.readLine().split("\\s+");
            char[] row = tks[0].toCharArray();
            boolean[] cakes = new boolean[row.length];

            for(int i=0; i< row.length; i++){
                if(row[i]=='+') cakes[i] = true;
            }

            int leftMost = 0;
            int k = Integer.parseInt(tks[1]);

            int count=0;

            for(int i=0; i<cakes.length-k+1; i++){

                if(cakes[i]!=true) leftMost=i;
                else continue;

                int flips = 0;
                for(int j=i; flips<k; j++){
                    cakes[j] = !cakes[j];
                    flips++;
                }
                count++;
            }

            boolean possible = false;
            int lastFew = k;
            for(int i = cakes.length-1; lastFew!=0; i--){
                if(cakes[i]==false) {
                    possible=false;
                    break;
                }
                possible = true;
                lastFew--;
            }

            //System.out.println(Arrays.toString(cakes));

            if(possible){
                System.out.printf("Case #%d: %d\n", t, count);
            }else{
                System.out.printf("Case #%d: IMPOSSIBLE\n", t);
            }

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
