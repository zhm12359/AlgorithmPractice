import java.io.*;
import java.util.*;
public class BathroomStall {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<T+1; t++){
            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0];
            int k = tks[1];

            PriorityQueue<Integer> pq = new PriorityQueue<>(1000, Collections.reverseOrder());

            int counter = 0;
            pq.offer(n);
            int min=n, max=n;
            while(counter<k){
                //System.out.println(pq);

                int cur = pq.poll();
                //System.out.println("after polling " + cur + "  " + pq);
                counter++;
                if(cur%2!=0){
                    int toNeighbor = cur/2;
                    min = toNeighbor;
                    max = toNeighbor;
                    pq.offer(toNeighbor);
                    pq.offer(toNeighbor);
                }else{

                    int left = (cur-1)/2;
                    int right = cur/2;

                    min = left;
                    max = right;

                    pq.offer(left);
                    pq.offer(right);
                }

            }

            //System.out.println(pq);
            System.out.printf("Case #%d: %d %d\n", t, max, min);

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