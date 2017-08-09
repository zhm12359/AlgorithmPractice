import java.io.*;
import java.util.*;
public class LargestPrimeFactor {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        PrintWriter pw = new PrintWriter(System.out);

        while(true){
            //int n = Integer.parseInt(br.readLine().trim());
            long n = sc.nextLong();
            if(n==0) break;
            if(n<0) n=-n;

            Map<Long, Integer> factors = new TreeMap<>();

            for (long d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    int a = 0;
                    while (n % d == 0) {
                        a++;
                        n = n/d;
                    }
                    factors.put(d, a);
                }
            }

            if (n > 1) {
                factors.put(n, 1);
            }

            if(factors.size()<2){
                System.out.println(-1);
            }else{

                long max = -1;
                for(long i:factors.keySet()){
                    max = i > max? i : max;
                }

                System.out.println(max);
            }

        }

    }


}