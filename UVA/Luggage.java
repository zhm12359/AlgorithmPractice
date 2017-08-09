import java.io.*;
import java.util.*;
public class Luggage {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            int[] set = lineToIntArray(br.readLine());
            int sum = 0;
            for(int i=0; i<set.length; i++) sum+=set[i];

            if(sum%2!=0) System.out.println("NO");
            else{
                boolean possible=false;
                int half = sum/2;
                int len = set.length;

                for (int j = 0; j < (1 << len ); j++) {
                    int total=0;
                    for (int k = 0; k < len; k++) {
                        if ((j & (1 << k)) != 0) { //use kth element in this mask j
                            total += set[k];
                            if(total==half){
                                possible=true;
                                break;
                            }else if(total>half){
                                break;
                            }
                        }
                    }

                    if(possible) break;

                }

                if(possible) System.out.println("YES");
                else System.out.println("NO");

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