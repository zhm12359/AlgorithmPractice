import java.io.*;
import java.util.*;
public class Defense {

    static BufferedReader br;
    static ArrayList<Integer> list;
    static HashMap<String, Integer> dp;
    static int count;
    static int ans;
    static int[] tailTable;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        br.readLine();

        while(T-->0){
            String line;
            list = new ArrayList<>();
            dp = new HashMap<>();
            count=0;

            while(true){
                line = br.readLine();
                if(line==null || line.equals("")) break;
                else{
                    list.add(Integer.parseInt(line));
                }
            }

            int n = list.size();
            int[] l = new int[n];
            for(int i=0; i<n; i++) l[i] = list.get(i);

            int[] liss = lis(l);
            System.out.printf("Max hits: %d\n", liss.length);
            for(int i=0; i<liss.length; i++) System.out.println(liss[i]);

//
//            ans = best(0, -1);
//            System.out.printf("Max hits: %d\n", ans);
//            printPath(0, -1);





            if(T>0) System.out.println();

        }
    }


    public static void printPath(int i, int p){

        if(i==list.size()) return;
        if(count==ans) return;
        String key = i + "---" + p;


        String choose = (i+1) + "---" + list.get(i);
        String ignore = (i+1) + "---" + p;


        if(dp.containsKey(choose) && dp.containsKey(ignore)) {
            int ch = dp.get(choose);
            int ig = dp.get(ignore);

            if(ch>=ig){
                System.out.println(list.get(i));
                count++;
                printPath(i+1, list.get(i));
            }else{
                printPath(i+1, p);
            }
        }else if( dp.containsKey(ignore)){
            printPath(i+1, p);
        }else {
            System.out.println(list.get(i));
            count++;
            printPath(i+1, list.get(i));
        }

    }


    public static int best(int i, int p){

        String key = i+"---"+p;
        if(dp.containsKey(key)) return dp.get(key);

        if(i==list.size()) return 0;

        if(list.get(i)<=p) return 0;

        int choose = 1+best(i+1, list.get(i));
        int ignore = best(i+1, p);

        if(choose>ignore){
            dp.put(key, choose);
            return choose;
        }else{
            dp.put(key, ignore);
        }

        return  dp.get(key);


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


    public static int[] lis(int[] a) {
        int n = a.length;
        int[] tail = new int[n];
        int[] prev = new int[n];

        int len = 0;
        for (int i = 0; i < n; i++) {
            int pos = lower_bound(a, tail, len, a[i]);
            if (pos == len) {
                ++len;
            }
            prev[i] = pos > 0 ? tail[pos - 1] : -1;
            tail[pos] = i;
        }

        int[] res = new int[len];
        for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
            res[--len] = a[i];
        }
        return res;
    }

    static int lower_bound(int[] a, int[] tail, int len, int key) {
        int lo = -1;
        int hi = len;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (a[tail[mid]] < key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

}