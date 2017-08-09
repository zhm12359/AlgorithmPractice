import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Census {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;
        int N = Integer.parseInt(br.readLine());
//        //int[][] house = new int[N][N];
//        int[] test = {0, 9, 10, 1, 3};
//        SegmentTree sta = new SegmentTree(test);
//        System.out.println(sta.getMin(0,4));


        SegmentTree[] house = new SegmentTree[N];

        for(int n=0; n<N; ++n){
            line = br.readLine().split(" ");
            int[] row = new int[N];
            for(int c=0; c<N; c++){
                row[c] = Integer.parseInt(line[c]);
            }
            house[n] = new SegmentTree(row);
        }

        int C = Integer.parseInt(br.readLine());
        //print(house);

        for(int t=0; t<C; ++t){
            line = br.readLine().split(" ");
            if(line[0].equals("c")){

                int x = Integer.parseInt(line[1]);
                int y = Integer.parseInt(line[2]);
                int v = Integer.parseInt(line[3]);

                house[x-1].update(y-1, v);
                //print(house);
            }else{
                int x1 = Integer.parseInt(line[1]);
                int y1 = Integer.parseInt(line[2]);
                int x2 = Integer.parseInt(line[3]);
                int y2 = Integer.parseInt(line[4]);
                int max = -1;
                int min = Integer.MAX_VALUE;

                for(int i=x1-1; i<=x2-1; ++i ){
                    SegmentTree st = house[i];
                    int curmax =  st.getMax(y1-1, y2-1);
                    int curmin =  st.getMin(y1-1, y2-1);
                    max = max > curmax ? max : curmax;
                    min = min < curmin ? min : curmin;
//                    System.out.println(Arrays.toString(st.list));
//                    System.out.println("max "+ st.getMax(y1-1, y2-1));
//                    System.out.println("min "+ st.getMin(y1-1,y2-1));


                }
                System.out.println(max + " " + min);
            }

        }


    }

    public static void print(SegmentTree[] sts){
        for(int i=0; i<sts.length; i++){
            System.out.println(Arrays.toString(sts[i].list));
        }
    }

    public static class SegmentTree {
        public int[] list;
        public int[] stmin;
        public int[] stmax;

        public SegmentTree(int[] list) {
            this.list = list;
            this.stmin = new int[4 * list.length];
            this.stmax = new int[4 * list.length];
            build(0, list.length - 1, 0);
        }

        // Initialize: build(0, list.size() - 1, 0);
        public void build(int nL, int nR, int n) {
            if (nL == nR){
                stmin[n] = nL;
                stmax[n] = nL;
            }
            else {
                int mid = (nL + nR) / 2, l = left(n), r = right(n);
                build(nL, mid, l);
                build(mid + 1, nR, r);
                stmin[n] = list[stmin[l]] <= list[stmin[r]] ? stmin[l] : stmin[r];
                stmax[n] = list[stmax[l]] > list[stmax[r]] ? stmax[l] : stmax[r];
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
            update(pos, value, 0, list.length - 1, 0);
        }

        // Update segment tree at given position with given value.
        // Current node n has index range [nL,nR]
        public void update(int pos, int value, int nL, int nR, int n) {
            if (nL == nR) {
                list[pos] = value;
                stmin[n] = pos;
                stmax[n] = pos;
            } else {
                int mid = (nL + nR) / 2, l = left(n), r = right(n);
                if (pos <= mid)
                    update(pos, value, nL, mid, l);
                else
                    update(pos, value, mid + 1, nR, r);
                stmin[n] = list[stmin[l]] <= list[stmin[r]] ? stmin[l] : stmin[r];
                stmax[n] = list[stmax[l]] > list[stmax[r]] ? stmax[l] : stmax[r];
            }
        }

        public int getMin(int L, int R){
            int idx = minQuery(L,R);
            return list[idx];
        }

        public int getMax(int L, int R){
            int idx = maxQuery(L,R);
            return list[idx];
        }

        public int maxQuery(int L, int R) {
            return maxQuery(L, R, 0, list.length - 1, 0);
        }


        public int maxQuery(int L, int R, int nL, int nR, int n) {
            if (L <= nL && nR <= R)
                return stmax[n];
            int lMax = -1, rMax = -1;
            int mid = (nL + nR) / 2;
            if (L <= mid)
                lMax = maxQuery(L, R, nL, mid, left(n));
            if (mid + 1 <= R)
                rMax = maxQuery(L, R, mid + 1, nR, right(n));
            if (lMax == -1 || rMax == -1)
                return lMax == -1 ? rMax : lMax;
            return list[lMax] > list[rMax] ? lMax : rMax;
        }

        public int minQuery(int L, int R) {
            return minQuery(L, R, 0, list.length - 1, 0);
        }

        // Get index of minimum value in index range [L,R]
        // Current node n has index range [nL,nR]
        public int minQuery(int L, int R, int nL, int nR, int n) {
            if (L <= nL && nR <= R)
                return stmin[n];
            int lMin = -1, rMin = -1;
            int mid = (nL + nR) / 2;
            if (L <= mid)
                lMin = minQuery(L, R, nL, mid, left(n));
            if (mid + 1 <= R)
                rMin = minQuery(L, R, mid + 1, nR, right(n));
            if (lMin == -1 || rMin == -1)
                return lMin == -1 ? rMin : lMin;
            return list[lMin] <= list[rMin] ? lMin : rMin;
        }


    }

    // min queries and max
//    public static class SegmentTree {
//        public int[] list;
//        public int[] stmin;
//        public int[] stmax;
//
//        public SegmentTree(int[] list) {
//            this.list = list;
//            this.stmin = new int[4 * list.length];
//            this.stmin = new int[4 * list.length];
//            build(0, list.length - 1, 0);
//        }
//
//        // Initialize: build(0, list.size() - 1, 0);
//        public void build(int nL, int nR, int n) {
//            if (nL == nR){
//                stmin[n] = list[nL];
//                stmax[n] = list[nL];
//            }
//            else {
//                int mid = (nL + nR) / 2, l = left(n), r = right(n);
//                build(nL, mid, l);
//                build(mid + 1, nR, r);
//                stmin[n] = stmin[l] > stmin[r]? stmin[r] : stmin[l] ;
//                stmax[n] = stmax[l] > stmax[r]? stmax[l] : stmax[r] ;
//            }
//        }
//
//        // Index of left child
//        int left(int n) {
//            return 2 * n + 1;
//        }
//
//        // Index of right child
//        int right(int n) {
//            return left(n) + 1;
//        }
//
//        public void update(int pos, int value) {
//            update(pos, value, 0, list.length - 1, 0);
//        }
//
//        // Update segment tree at given position with given value.
//        // Current node n has index range [nL,nR]
//        public void update(int pos, int value, int nL, int nR, int n) {
//            if (nL == nR) {
//                stmin[n] = value;
//                stmax[n] = value;
//            } else {
//                int mid = (nL + nR) / 2, l = left(n), r = right(n);
//                if (pos <= mid)
//                    update(pos, value, nL, mid, l);
//                else
//                    update(pos, value, mid + 1, nR, r);
//                stmin[n] = stmin[l] > stmin[r]? stmin[r] : stmin[l] ;
//                stmax[n] = stmax[l] > stmax[r]? stmax[l] : stmax[r] ;
//            }
//        }
//
//        public int getMin(int L, int R){
//            return getMin(L, R, 0, list.length-1, 0);
//        }
//
//        public int getMin(int L, int R, int nL, int nR, int n){
//            if (L <= nL && nR <= R)
//                return stmin[n];
//            else{
//                int mid = (nL+nR)/2;
//                int lminValue = getMin(L, R, nL, mid, left(n));
//                int rminValue = getMin(L, R, mid+1, nR, right(n));
//
//                return lminValue > rminValue ? rminValue : lminValue;
//
//            }
//
//        }
//
//        public int getMax(int L, int R){
//            return getMax(L, R, 0, list.length-1, 0);
//        }
//
//        public int getMax(int L, int R, int nL, int nR, int n){
//            if (L <= nL && nR <= R)
//                return stmin[n];
//            else{
//                int mid = (nL+nR)/2;
//                int lmaxValue = getMax(L, R, nL, mid, left(n));
//                int rmaxValue = getMax(L, R, mid+1, nR, right(n));
//
//                return lmaxValue < rmaxValue ? rmaxValue : lmaxValue;
//
//            }
//
//        }
//
//
//
//
//    }
}

