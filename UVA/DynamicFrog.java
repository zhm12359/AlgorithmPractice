import java.io.*;
import java.util.*;
public class DynamicFrog {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<T+1; ++t){
            int[] tokens = lineToIntArray(br.readLine());
            int sn = tokens[0];
            int d = tokens[1];

            ArrayList<Integer> road = new ArrayList<>();
            road.add(0);
            road.add(0);

            String[] stones = br.readLine().split("\\s+");

            for(int i=0; i<sn; ++i){
                String[] tks = stones[i].split("-");
                road.add(Integer.parseInt(tks[1]));
                if(tks[0].equals("B")) road.add(Integer.parseInt(tks[1]));
            }

            road.add(d);
            road.add(d);

            //System.out.println(road);

            int max=0;

            for(int i=0; i+2<road.size(); i+=2){
                max = Math.max(road.get(i+2) - road.get(i), max);
            }

            for(int i=1; i+2<road.size(); i+=2){
                max = Math.max(road.get(i+2) - road.get(i), max);
            }

            pw.printf("Case %d: %d\n", t, max );


        }
        pw.flush();

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