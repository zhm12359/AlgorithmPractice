import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class GasStation {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){

            int[] tokens = lineToIntArray(br.readLine());
            int L = tokens[0];
            int N = tokens[1];

            if(L==0&&N==0) break;

            Pair[] pairs = new Pair[N];

            for(int n=0; n<N; ++n){
                tokens = lineToIntArray(br.readLine());
                pairs[n] = new Pair(tokens[0] - tokens[1], tokens[0]+tokens[1]);
            }

            Arrays.sort(pairs);

            int pos=0;
            int currentRightMax=0;
            int need=0;

            while(pos<L){

                currentRightMax=pos;

                for(int i=0; i<N&&pairs[i].left<=pos; ++i){
                    //System.out.println(pairs[i].right);
                    currentRightMax = pairs[i].right > currentRightMax ? pairs[i].right : currentRightMax;
                }

                if(pos==currentRightMax) break;
                pos=currentRightMax;
                need++;

            }

            if(pos<L){
                System.out.println(-1);
            }else{
                System.out.println(N-need);
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

    public static class Pair implements Comparable<Pair> {

        int left;
        int right;

        public Pair(int l, int r){
            this.left = l;
            this.right = r;
        }

        public int compareTo(Pair other){
            return Integer.compare(this.left, other.left);
        }

        public String toString(){
            return "("+left + " " + right +")";
        }

    }

}
