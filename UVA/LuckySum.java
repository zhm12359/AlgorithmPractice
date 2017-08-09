import java.io.*;
import java.util.*;
public class LuckySum {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; ++t){

            int[] tokens = lineToIntArray(br.readLine());
            int N = tokens[0];
            int Q = tokens[1];

            int[] array = lineToIntArray(br.readLine());

            HashMap<Integer, Integer> occurance = new HashMap<>();
            for(int i=0; i<N; ++i){
                if(!occurance.containsKey(array[i])) occurance.put(array[i], 1);
                else{
                    occurance.put(array[i], occurance.get(array[i])+1);

                }
            }

            Arrays.sort(array);

            for(int q=0; q<Q; ++q){
                int sum=Integer.parseInt(br.readLine());
                int count=0;

                int toLeft=0;
                int prev = -1;
                for(int i=0; i<N; ++i){
                    toLeft++;

                    int cur = array[i];
                    if(cur==prev) continue;
                    int left = sum-cur;
                    int[] subarray = Arrays.copyOfRange(array, i+1, array.length);

                    int res = Arrays.binarySearch(subarray, left);

                    if(res>=0){
                        if(cur+cur!=sum)count+= occurance.get(cur)*occurance.get(left);
                        else {
                            int sss = occurance.get(cur)-1;
                            count += (1+sss)*sss/2;
                        }
                    }
                    prev=cur;
                }
                System.out.println(count);

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
}