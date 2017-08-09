import java.io.*;
import java.util.*;
public class Potentiometers {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine());


        int cases=1;
        while(true){


            if(N==0) break;
            pw.printf("Case %d:\n", cases++);

            ArrayList<Integer> list = new ArrayList<>();

            for(int i=0; i<N; ++i){
                list.add(Integer.parseInt(br.readLine()));
            }

            SegmentTree st = new SegmentTree(list);

            while(true){
                String cmd = br.readLine();
                if(cmd.equals("END")) break;

                String[] tokens = cmd.split("\\s+");
                String c = tokens[0];
                int a = Integer.parseInt(tokens[1]);
                int b = Integer.parseInt(tokens[2]);

                if(c.equals("M")){
                    pw.println(st.rangeQuery(a-1,b-1));
                }else{
                    st.update(a-1, b);
                }

            }
            N = Integer.parseInt(br.readLine());
            if(N!=0) pw.println();

        }
        pw.flush();



    }

    // sum seg tree
    public static class SegmentTree {
        public ArrayList<Integer> list;
        public int[] st;

        public SegmentTree(ArrayList<Integer> list) {
            this.list = list;
            this.st = new int[4 * list.size() + 100];
            build(0, list.size() - 1, 0);
        }

        // Initialize: build(0, list.size() - 1, 0);
        public void build(int nL, int nR, int n) {
            if (nL == nR) {
                st[n] = list.get(nL);
                return;
            }
            else {
                int mid = (nL + nR) / 2, l = left(n), r = right(n);
                build(nL, mid, l);
                build(mid + 1, nR, r);
                st[n] =  st[l] + st[r];
            }
        }

        // Index of left child
        int left(int n) {
            return 2 * n + 1;
        }

        // Index of right child
        int right(int n) {
            return left(n) + 1;
        }

        public void update(int pos, int value) {
            update(pos, value, 0, list.size() - 1, 0);
        }

        // Update segment tree at given position with given value.
        // Current node n has index range [nL,nR]
        public void update(int pos, int value, int nL, int nR, int n) {
            if(pos<nL || pos>nR) return;
            if (nL == nR) {
                st[n] = value;
                return;
            } else {
                int mid = (nL + nR) / 2, l = left(n), r = right(n);

                update(pos, value, nL, mid, l);
                update(pos, value, mid + 1, nR, r);
                st[n] = st[l] + st[r];
            }
        }

        public int rangeQuery(int l, int r){
            return rangeQuery(l,r, 0, list.size()-1, 0);
        }

        public int rangeQuery(int l, int r, int nL, int nR, int n){
            if(l>nR || r < nL) return 0;
            if(l<=nL && r>=nR) return st[n];
            int mid = (nL+nR)/2;
            int left =left(n);
            int right = right(n);

            return rangeQuery(l,r, nL, mid, left) + rangeQuery(l,r, mid+1, nR, right);
        }

//        public int minQuery(int L, int R) {
//            return minQuery(L, R, 0, list.size() - 1, 0);
//        }
//
//        // Get index of minimum value in index range [L,R]
//        // Current node n has index range [nL,nR]
//        public int minQuery(int L, int R, int nL, int nR, int n) {
//            if (L <= nL && nR <= R)
//                return st[n];
//            int lMin = -1, rMin = -1;
//            int mid = (nL + nR) / 2;
//            if (L <= mid)
//                lMin = minQuery(L, R, nL, mid, left(n));
//            if (mid + 1 <= R)
//                rMin = minQuery(L, R, mid + 1, nR, right(n));
//            if (lMin == -1 || rMin == -1)
//                return lMin == -1 ? rMin : lMin;
//            return list.get(lMin) <= list.get(rMin) ? lMin : rMin;
//        }
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