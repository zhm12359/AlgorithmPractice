import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class CriticalElements {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            int size = Integer.parseInt(br.readLine());



            if(size==1){

                System.out.println(br.readLine());
                continue;
            }

            int[] a = lineToIntArray(br.readLine());





            ArrayList<Integer> critical = new ArrayList<>();
            int olen = LongestIncreasingSubsequenceLength(a, size);

            for(int i=0; i<size; i++){

                int[] temp = new int[size-1];
                int removeIndex = i;
                int cur =0;

                int p =0;
                while(p<size-1 && cur<size){

                    if(cur==removeIndex){
                        cur++;
                    }else{
                        temp[p] = a[cur];
                        p++;
                        cur++;
                    }
                }

                //System.out.println(Arrays.toString(temp));

                int curLIS = LongestIncreasingSubsequenceLength(temp, size-1);
                if(curLIS<olen){
                    critical.add(a[i]);
                }

            }

            if(critical.size()==0){
                System.out.println(-1);
            }else{
                for(int i=0; i<critical.size(); i++){
                    if(i<critical.size()-1) System.out.printf("%d ",critical.get(i));
                    else System.out.println(critical.get(i));

                }
            }



        }

    }

    static int CeilIndex(int A[], int l, int r, int key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m]>=key)
                r = m;
            else
                l = m;
        }

        return r;
    }

    static int LongestIncreasingSubsequenceLength(int A[], int size)
    {
        // Add boundary case, when array size is one

        int[] tailTable = new int[size];
        int len; // always points empty slot

        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++)
        {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];

            else if (A[i] > tailTable[len-1])
                // A[i] wants to extend largest subsequence
                tailTable[len++] = A[i];

            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];
        }

        return len;
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

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }
}
