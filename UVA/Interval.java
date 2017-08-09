import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Interval {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        while(true){

            String[] token = line.split(" ");
            int arrayLen = Integer.parseInt(token[0]);
            int commands = Integer.parseInt(token[1]);

            String[] list = br.readLine().split(" ");
            ArrayList<Integer> ints = new ArrayList<Integer>();

            for(int i=0; i<arrayLen; ++i){
                int cur = Integer.parseInt(list[i]);
                if(cur>=1) ints.add(1);
                else if(cur<=-1) ints.add(-1);
                else ints.add(0);
                //ints.add(Integer.parseInt(list[i]));
            }

            SegmentTree st = new SegmentTree(ints);
            //System.out.println("orginal\n"+ints);

            for(int c=0; c<commands; ++c){
                token = br.readLine().split(" ");
                String cmd = token[0];
                int x = Integer.parseInt(token[1]);
                int y = Integer.parseInt(token[2]);

                if(cmd.equals("C")){
                    int value;
                    if(y>1) value=1;
                    else if(y<-1) value=-1;
                    else value = y;

                    st.update(x-1, value);
                    ints.set(x-1, value);
                    //System.out.println("Changing "+ (x-1) + " to " + y);
                    //System.out.println(ints);
                }else{

                    int res = st.rangeQuery(x-1,y-1);
                   // System.out.println("Querying "+ (x-1) + " to " + (y-1) +" yield " + res );
                    if(res==1) System.out.print("+");
                    else if(res==-1) System.out.print("-");
                    else System.out.print(0);
                }
            }

            System.out.print("\n");

            line=br.readLine();
            if(line==null) break;

        }

    }

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
                st[n] =  st[l] * st[r];
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
                st[n] = st[l] * st[r];
            }
        }

        public int rangeQuery(int l, int r){
            return rangeQuery(l,r, 0, list.size()-1, 0);
        }

        public int rangeQuery(int l, int r, int nL, int nR, int n){
            if(l>nR || r < nL) return 1;
            if(l<=nL && r>=nR) return st[n];
            int mid = (nL+nR)/2;
            int left =left(n);
            int right = right(n);

            return rangeQuery(l,r, nL, mid, left) * rangeQuery(l,r, mid+1, nR, right);
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
}
