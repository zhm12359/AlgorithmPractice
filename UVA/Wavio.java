import java.io.*;
import java.util.*;
public class Wavio {

    //static BufferedReader br;

    public static void main(String[] args) throws Exception {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        Scanner sc = new Scanner(System.in);



        while(sc.hasNext()){



            int n = sc.nextInt();
            int[] array = new int[n];

            for(int i=0; i<n; i++){
                array[i] = sc.nextInt();
            }

            int[] dp1 = LongestIncreasingSubsequenceLength(array, n);

            for(int i = 0; i < array.length / 2; i++)
            {
                int temp = array[i];
                array[i] = array[array.length - i - 1];
                array[array.length - i - 1] = temp;
            }

            int[] dp2 = LongestIncreasingSubsequenceLength(array, n);

            int i=0, j=n-1;
            int max=0;
            while(i<n && j>=0){

                int a = dp1[i];
                int b = dp2[j];

                int min = Math.min(a-1, b-1);

                max = Math.max(max, min*2+1);

                i++;
                j--;

            }

            System.out.println(max);

        }

//        while((line=br.readLine())!=null){
//            if(line.equals("")) continue;
//            if(line==null) break;
//
//
//            int n = Integer.parseInt(line);
//            int[] array = lineToIntArray(br.readLine());
//
//            int[] dp1 = LongestIncreasingSubsequenceLength(array, n);
//
//            for(int i = 0; i < array.length / 2; i++)
//            {
//                int temp = array[i];
//                array[i] = array[array.length - i - 1];
//                array[array.length - i - 1] = temp;
//            }
//
//            int[] dp2 = LongestIncreasingSubsequenceLength(array, n);
//
//            int i=0, j=n-1;
//            int max=0;
//            while(i<n && j>=0){
//
//                int a = dp1[i];
//                int b = dp2[j];
//
//                int min = Math.min(a-1, b-1);
//
//                max = Math.max(max, min*2+1);
//
//                i++;
//                j--;
//
//            }
//
//            System.out.println(max);
//
//        }
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



    static int[] LongestIncreasingSubsequenceLength(int A[], int size)
    {
        // Add boundary case, when array size is one

        int[] tailTable   = new int[size];
        int[] dp = new int[size];
        dp[0] =1;
        int len; // always points empty slot

        tailTable[0] = A[0];
        len = 1;
        for (int i = 1; i < size; i++)
        {
            if (A[i] < tailTable[0])
                // new smallest value
                tailTable[0] = A[i];

            else if (A[i] > tailTable[len-1]){
                tailTable[len++] = A[i];
            }
            // A[i] wants to extend largest subsequence



            else
                // A[i] wants to be current end candidate of an existing
                // subsequence. It will replace ceil value in tailTable
                tailTable[CeilIndex(tailTable, -1, len-1, A[i])] = A[i];

            dp[i] = len;
        }

        //System.out.println(Arrays.toString(dp));

        return dp;
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