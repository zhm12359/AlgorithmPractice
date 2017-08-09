

import java.io.*;
import java.util.*;
public class Risk {

    static BufferedReader br;
    static int[][] edgeCost;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line;
        int test = 1;
        while((line=br.readLine())!=null){


            edgeCost = new int[20][20];

            for(int i=0; i<20; i++){
                for(int j=0; j<20; j++){
                    edgeCost[i][j]=500;
                }
            }

            for(int i=0; i<19; i++){

                int[] tks = lineToIntArray(line);
                int n = tks[0];

                for(int c=1; c<tks.length; c++){
                    int cur = tks[c]-1;
                    edgeCost[i][cur]=1;
                    edgeCost[cur][i]=1;
                }

                line = br.readLine();
            }

            //fw
            for(int k=0; k<20; k ++){
                for(int i=0; i<20; i++){
                    for(int j=0; j<20; j++){
                        if(edgeCost[i][j] > edgeCost[i][k] + edgeCost[k][j]){
                            edgeCost[i][j] = edgeCost[i][k] + edgeCost[k][j];
                        }
                    }
                }
            }

            int q = Integer.parseInt(line);

            System.out.printf("Test Set #%d\n", test);
            test++;

            for(int i=0; i<q; i++){
                int[] tks = lineToIntArray((line=br.readLine()));
                int min = edgeCost[tks[0]-1][tks[1]-1];

                String s = tks[0] > 9 ? ""+tks[0] : " "+tks[0];
                String to = tks[1] > 9 ? ""+tks[1] : " "+tks[1];
                System.out.printf("%s to %s: %d\n", s, to, min);

            }

            System.out.println();

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