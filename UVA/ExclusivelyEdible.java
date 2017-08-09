import java.io.*;
import java.util.*;
//NIM GAME
public class ExclusivelyEdible {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int[] tks = lineToIntArray(br.readLine());
            int m = tks[0], n = tks[1], r=tks[2], c = tks[3];

            int bottom = m - r - 1;
            int top = m - bottom -1;
            int right = n - c -1;
            int left = n - right - 1;

            int nim = bottom ^ top ^ right ^ left;

            if(nim==0){
                System.out.println("Hansel");
            }else{
                System.out.println("Gretel");
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