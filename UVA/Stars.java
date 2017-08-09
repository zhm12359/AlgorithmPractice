import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Stars {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] level = new int[32002];
        Fenwick ft = new Fenwick(32002);

        int N = Integer.parseInt(br.readLine());
        String[] line;

        for(int n=0; n<N; ++n){

            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            level[ft.sumQuery(x+1)] ++;
            ft.adjust(x+1, 1);

        }

        //System.out.println(Arrays.toString(level));

        for(int i=0; i<N; ++i){
            System.out.println(level[i]);
        }

    }




    static public class Fenwick {
        public int[] table;

        public Fenwick(int maxN) {
            this.table = new int[maxN + 1];
        }

        public int sumQuery(int a, int b) {
            return sumQuery(b) - sumQuery(a - 1);
        }

        public int sumQuery(int k) {
            int ret = 0;
            while (k > 0) {
                ret += table[k];
                k &= k - 1;
            }
            return ret;
        }

        public void adjust(int i, int adj) {
            while (i < table.length) {
                table[i] += adj;
                i += (i & (-i));
            }
        }

        public int getValue(int i) {
            return sumQuery(i, i);
        }

        // Assumes entries of list are non-negative (i.e., cumulative sums are
        // increasing)
        // Returns first index whose cumulative sum is >= k
        // Returns -1 if all are less
        public int findFirst(int k) {
            int L = 1, R = table.length - 1;
            while (R - L > 1) {
                int M = (R + L) / 2;
                int val = sumQuery(M);
                if (val < k)
                    L = M + 1;
                else
                    R = M;
            }
            int LVal = sumQuery(L);
            if (LVal >= k)
                return L;
            return R == L || sumQuery(R) < k ? -1 : R;
        }
    }
}
