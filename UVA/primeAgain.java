import java.io.*;
import java.util.*;
public class primeAgain {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            System.out.printf("Case %d:\n", t);

            int[] tks = lineToIntArray(br.readLine());
            int m = tks[0];
            int n = tks[1];

            TreeMap<Long,Integer> map1 = factorize(m);
            TreeMap<Long,Integer> map2 = new TreeMap<>();
            for(int i=1; i<=n; i++){
                TreeMap<Long,Integer> temp = factorize(i);

                for(long key:temp.keySet()){
                    if(map2.containsKey(key)){
                        map2.put(key,
                                map2.get(key)
                                +temp.get(key));
                    }else{
                        map2.put(key, temp.get(key));
                    }
                }
            }

//            System.out.println(map1);
//            System.out.println(map2);
            int min = Integer.MAX_VALUE;

            for(long a: map1.keySet()){
                int p =map2.get(a)/ map1.get(a);
//
//                System.out.println(map1.get(a)  + "  " + map2.get(a));
                min = Math.min(p, min);
            }

            if(min!=Integer.MAX_VALUE) System.out.println(min);
            else{
                System.out.println("Impossible to divide");
            }



        }


    }

    static TreeMap<Long, Integer> factorize(long n){
        TreeMap<Long, Integer> factors = new TreeMap<>();

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

        return factors;
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