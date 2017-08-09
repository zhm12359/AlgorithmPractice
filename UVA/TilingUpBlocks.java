
import java.io.*;
import java.util.*;
public class TilingUpBlocks {

    //static BufferedReader br;

    public static void main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);

        while(true){

            int n = sc.nextInt();
            if(n==0) break;
            int[][] tiles = new int[101][101];
            int[][] max = new int[101][101];

            for(int i=0; i<n; ++i){
                //int[] tks = lineToIntArray(br.readLine());
                int l = sc.nextInt();
                int m = sc.nextInt();
                tiles[l][m] +=1;
            }


            for(int i=1; i<101; ++i){
                for(int j=1; j<101; ++j){

                    max[i][j] = Math.max(max[i-1][j], max[i][j-1]); // can only choose tiles on either direction
                    max[i][j] += tiles[i][j]; //self

                }
            }

            pw.println(max[100][100]);



        }
        pw.println("*");
        pw.flush();
        sc.close();

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