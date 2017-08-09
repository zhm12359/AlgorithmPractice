import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;
public class NOD {

    static BufferedReader br;
    static ArrayList<Integer> sequence = new ArrayList<Integer>();
    static int[] NOD = new int[1000001];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        init();


        for(int t=0; t<T; ++t){
            int[] tokens = lineToIntArray(br.readLine());
            int l = tokens[0];
            int r = tokens[1];
            int left = indexOf(l, "left");
            int right = indexOf(r, "right");
            pw.printf("Case %d: %d\n", t+1  ,right - left + 1);
            pw.flush();
        }
    }

    public static void init(){
        sequence.add(1);
        int i=0;

        while(i<=1000000){
            int prev = sequence.get(sequence.size()-1);
            i = nod(prev) +prev;
            sequence.add(i);
        }
    }

    //binary search
    // further optimization idea from https://github.com/munguial/uva_problems/blob/master/11876%20-%20N%20%2B%20NOD%20(N)/Main.java
    public static int indexOf( int key, String pos) {
        int lo = 0;
        int hi = sequence.size()-1;
        int mid =0;
        while (lo <= hi) {
            mid =  (hi + lo) / 2;
            if(key < sequence.get(mid)) {
                hi = mid - 1;
            }
            else if (key > sequence.get(mid)){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        if(pos.equals("right")){
            return sequence.get(mid)>key?mid-1:mid;
        }else{
            return sequence.get(mid)<key?mid+1:mid;
        }

    }
    //from online
    //https://github.com/munguial/uva_problems/blob/master/11876%20-%20N%20%2B%20NOD%20(N)/Main.java
    public static int nod(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        if(NOD[n]!=0) return NOD[n];

        int root =(int) Math.sqrt(n);

        int count=0;
        for(int i=2;i<=root;++i){
            if(n%i==0) {
                if(n/i == i) ++count;
                else count+=2;
            }
        }

        NOD[n] = count+2;
        return count+2;
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