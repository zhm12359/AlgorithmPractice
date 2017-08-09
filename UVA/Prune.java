import java.io.*;
import java.util.*;
public class Prune {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){
            int[] tokens = lineToIntArray(br.readLine());
            int n=tokens[0];
            int m=tokens[1];

            int[] a = lineToIntArray(br.readLine());
            int[] b =lineToIntArray(br.readLine());

            Arrays.sort(a);
            Arrays.sort(b);

            int i=0, j=0, count=0;

            while(i<n && j< m){

                int cura = a[i];
                int curb = b[j];
//                pw.printf("i: %d  j: %d\n", i,j);
//                pw.printf("cura: %d  curb: %d\n", cura,curb);
//                pw.println();

                if(cura<curb){
                    i++;
                    count++;
                }else if(cura>curb){
                    j++;
                    count++;
                }else{
                    i++;
                    j++;
                }

            }

            count += (n-i);
            count += (m-j);

            System.out.println(count);

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