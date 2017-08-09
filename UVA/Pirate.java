import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//seg tree code and idea from https://github.com/RedGreenCode/UVa/blob/master/11402/Main.java
public class Pirate {

    static BufferedReader br;
    static int[] original = new int[1100000];

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(br.readLine());
        int pairs, times, Q;
        String line;
        String[] command;
        String god, description;
        int left, right;
        int size;

        for(int c=1; c<cases+1; ++c){
            System.out.println("Case "+c+":");
            pairs = Integer.parseInt(br.readLine());
            description="";
            size = 0;
            for(int p=0; p<pairs; ++p){
                times = Integer.parseInt(br.readLine());
                line = br.readLine();
                int slen = line.length();
                int[] ints = new int[slen];
                for (int k=0; k<slen; k++) ints[k] = line.charAt(k) - '0';
                for(int t=0; t<times; t++){
                    for (int k=0; k<slen; k++) {
                        original[size++] = ints[k];
                    }
                }
            }

            SegmentTree st = new SegmentTree(size);

            Q = Integer.parseInt(br.readLine());
            int query=1;
            for(int q=1; q<Q+1; ++q){
                command = br.readLine().split(" ");
                god = command[0];
                left = Integer.parseInt(command[1]);
                right = Integer.parseInt(command[2]);

                if(god.equals("F")) st.update(1, left, right, st.SET);
                else if(god.equals("E")) st.update(1,left,right, st.CLEAR);
                else if(god.equals("I")) st.update(1, left, right, st.FLIP);
                else if(god.equals("S")){
                    System.out.println("Q"+ query++ +": "+ st.sumQuery(left,right));
                }

            }
        }

    }


    //sum queries
    public static class SegmentTree {
        public ArrayList<Integer> list;
        private int[] sum;
        private int[] pendingVal;	// defaults to 0 (DONOTHING)
        private int[] nfrom;
        private int[] nto;
        private int[] nsize;

        private int heapSize;

        public final int DONOTHING = 0, SET = 10, CLEAR = 20, FLIP = 30;

        public SegmentTree(int size) {

            heapSize = size *4;	// larger than necessary
            sum = new int[heapSize];
            pendingVal = new int[heapSize];
            nfrom = new int[heapSize];
            nto = new int[heapSize];
            nsize = new int[heapSize];

            build(1, 0, size);
        }

        private void build(int v, int from, int size) {
            nfrom[v] = from;
            nto[v] = from + size - 1;
            nsize[v] = size;

            if (size == 1) {
                sum[v] = original[from];
            } else {
                // Build children
                int c1 = v*2;
                int c2 = c1+1;
                int mid = size/2;
                build(c1, from, mid);
                build(c2, from + mid, size - mid);

                // Sum of range = sum of first half + sum of second half
                sum[v] = sum[c1] + sum[c2];
            }
        }

        private void update(int v, int from, int to, int value) {
            propagate(v);		// update node sum and child pending values

            // If the target range completely contains the node range, then we
            // can (lazily) update the whole node range to the new value.
            if (contains(from, to, nfrom[v], nto[v])) {

                change(v, value);
                return;
            }

            // If the previous conditions is false but the target range overlaps
            // with the start or the end of the node range, then we have to update
            // part of it, but we don't yet know which part. To get that information,
            // we need to travel farther down the tree to find nodes with more targeted ranges.
            if (intersects(from, to, nfrom[v], nto[v])) {
                int c1 = 2*v;
                int c2 = c1+1;
                update(c1, from, to, value);
                update(c2, from, to, value);

                sum[v] = sum[c1] + sum[c2];
            }

            // If none of the previous conditions are true, then the target range falls completely
            // before or completely after the range of node v. Therefore, we don't need to update
            // node v other than propagating any pending values.
        }

        // Range sum query for range [from, to], starting from the root
        public int sumQuery(int left, int right) {

            return RSQ(1, left, right);

        }

        public int RSQ(int vertex, int from, int to){

            if (contains(nfrom[vertex], nto[vertex], from, to)) {
                // All values are 1, so sum is the range size
                if (pendingVal[vertex] == SET) return (to - from + 1);

                // All values are 0, so sum is 0
                if (pendingVal[vertex] == CLEAR) return 0;
            }

            // If the target range completely contains the node range, then its whole sum
            // contributes to the range sum.
            if (contains(from, to, nfrom[vertex], nto[vertex])) {
                propagate(vertex);		// update node sum and child pending values
                return sum[vertex];
            }

            // If none of the previous conditions are true but the target range overlaps
            // with the start or the end of the node range, then it contributes part of its
            // sum the target sum, but we don't yet know how much. To get that information,
            // we need to travel farther down the tree to find nodes with more targeted ranges.
            if (intersects(from, to, nfrom[vertex], nto[vertex])) {
                propagate(vertex);		// update node sum and child pending values
                int c1 = vertex*2;
                int c2 = c1+1;
                int leftSum = RSQ(c1, from, to);
                int rightSum = RSQ(c2, from, to);

                return leftSum + rightSum;
            }

            // If none of the previous conditions are true, then the target range falls completely
            // before or completely after the range of node v. Therefore, node v's range does
            // not contribute to the target sum.
            return 0;



        }



        // If node v has a pending value, update its sum and propagate the pending value
        // to its children (if it has any).
        private void propagate(int vertex) {
            if (pendingVal[vertex] == DONOTHING) return;
            change(vertex, pendingVal[vertex]);
            pendingVal[vertex] = DONOTHING; //reset the pending propagation value
        }

        // Update node v's sum using a value, and set the pending values
        // for any child nodes so they can be lazily updated when necessary.
        private void change(int v, int value) {
            if (value == DONOTHING) return;
            sum[v] = getSum(v, value);

            // If v is a leaf node, there's nothing more to update
            if (nfrom[v] == nto[v]) return;

            // Otherwise, update children
            int c1 = v*2; //left child
            int c2 = c1+1; //right child
            if (value == SET || value == CLEAR) {
                // For SET and CLEAR, we can directly update the children,
                // since every array position will get the same value.
                pendingVal[c1] = pendingVal[c2] = value;
            } else {
                // For FLIP, every array position could end up with a
                // different value, so the new pending value depends
                // on the previous pending value.
                pendingVal[c1] = newPendingVal(c1);
                pendingVal[c2] = newPendingVal(c2);
            }
        }

        // This is used for a FLIP action, so return the opposite of the current value
        private int newPendingVal(int v) {
            switch(pendingVal[v]) {
                case CLEAR: return SET;
                case SET: return CLEAR;
                case FLIP: return DONOTHING;
                case DONOTHING: return FLIP;
            }
            // Possible error...
            return Integer.MIN_VALUE;
        }

        //Calculate the sum of a node range based on a value
        private int getSum(int v, int value) {
            switch(value) {
                case CLEAR:
                    // All elements are 0, so sum is 0
                    return 0;
                case SET:
                    // All elements are 1, so sum is the range size
                    return nsize[v];
                case FLIP:
                    // Each element flips to its opposite, so the
                    // sum is also the "opposite" of the previous sum.
                    //System.out.println("v" + v + "nsize[v]" +nsize[v]+" sum [v]" + sum[v]);
                    return nsize[v] - sum[v];
            }

            // Getting here would be an error, so make sure the test case fails
            return Integer.MIN_VALUE;
        }



        // Returns true if the range [from1, to1] completely contains the
        // range [from2, to2], so that no element of the second range is outside
        // of the first range.
        private boolean contains(int from1, int to1, int from2, int to2) {
            return from2 >= from1 && to2 <= to1;
        }


        // test if the given range intersects.
        // contains will be always called first to reduce the possibility
        private boolean intersects(int from1, int to1, int from2, int to2) {
            return from1 <= from2 && to1 >= from2   //  (.[..)..] or (.[...]..)
                    || from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
        }
    }

}

// refered: https://github.com/firebotQL/Competitive-Programming/blob/master/UVa/Competitive%20Programming%202/Chapter%202/11402%20-%20Ahoy%2C%20Pirates!/Main2.java













/* Fenwick Tree solution -- time limit exceeded
public class Pirate {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        int cases = readInt();
        int pairs, times, Q;
        String line, description;
        String[] command;
        String god;
        int left, right;

        for(int c=1; c<cases+1; ++c){
            System.out.println("Case "+c+":");
            pairs = readInt();
            description="";

            for(int p=0; p<pairs; ++p){
                times = readInt();
                line = br.readLine();

                for(int t=0; t<times; t++){
                    description+=line;
                }
            }

            int[] original = toIntArray(description);
            Fenwick ft = new Fenwick(original.length);

            //build the fenwick tree
            for(int i=1; i<original.length+1; ++i){
                ft.adjust(i,original[i-1]);
            }

            //test ft:
            //System.out.println(ft.sumQuery(4,5));

            Q = readInt();
            int query=1;
            for(int q=1; q<Q+1; ++q){
                command = br.readLine().split(" ");
                god = command[0];
                left = Integer.parseInt(command[1])+1;
                right = Integer.parseInt(command[2]) + 1;

                if(god.equals("F")){
                    for(int idx=left; idx<=right; ++idx){
                        int status = original[idx-1];
                        if(status==0) ft.adjust(idx, 1);
                    }
                    updateList(original, left-1, right-1, 1);
                }else if(god.equals("E")){
                    for(int idx=left; idx<=right; ++idx){
                        int status = original[idx-1];
                        if(status==1) ft.adjust(idx, -1);
                    }
                    updateList(original, left-1, right-1, 0);
                }else if(god.equals("I")){
                    for(int idx=left; idx<=right; ++idx){
                        int status = original[idx-1];
                        if(status==1) ft.adjust(idx, -1);
                        else ft.adjust(idx, 1);
                    }
                    flipRange(original, left-1, right-1);
                }else if(god.equals("S")){
                    System.out.println("Q"+ query++ +": "+ft.sumQuery(left,right));
                }

            }
        }

    }

    public static void updateList(int[] list, int l, int r, int value){
        for(int i=l; i<=r; ++i){
            list[i] = value;
        }
    }

    public static void flipRange(int[] list, int l, int r){
        for(int i=l; i<=r; ++i){
            if(list[i]==1) list[i]=0;
            else list[i]=1;
        }
    }

    public static int[] toIntArray(String s){

        int len = s.length();
        int[] res = new int[len];
        String[] sl = s.split("");

        for(int i=0; i<len; ++i){
            res[i] = Integer.parseInt(sl[i]);
        }

        return res;

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

    }


    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    static int readInt() throws Exception {
        int c = br.read();
        while (isWhitespace(c)) {
            c = br.read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = br.read();
        }
        int res = 0;
        do {
            res *= 10;
            res += c - '0';
            c = br.read();
        } while (!isWhitespace(c));
        return res * sgn;
    }
}
*/