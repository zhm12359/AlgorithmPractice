import java.io.*;
import java.util.*;
public class PowerString {

    static BufferedReader br;
    static String line;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(!(line=br.readLine()).equals(".")){

            int n = line.length();
            int ans = 1;

            for(int i=1; i<=n; i++){

                if( n%i==0 && check(i)) {

                    //System.out.printf("n %d   i %d\n", n, i);
                    ans = n/i;
                    break;
                }
            }

            System.out.println(ans);


        }

    }

    public static boolean check(int i){

        String unit = line.substring(0, i);
        //System.out.println("unit: " + unit);

        for(int j=i; j+i<=line.length(); j+=i ){
            String cur = line.substring(j, j+i);
            //System.out.printf("cur %s   j %d\n", cur, j);
            if(!cur.equals(unit)){
                return false;
            }
        }
        return true;



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