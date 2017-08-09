import java.io.*;
import java.util.*;
public class Hats {

    static BufferedReader br;
    static long[] fac;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        fac = new long[13];

        for(int i=0; i<13; i++){
            fac[i] = i;
        }
        for(int i=2; i<13;i++){
            fac[i] = fac[i]*fac[i-1];
        }


//        for(int i=0; i<13; i++){
//            solve(i);
//        }

        //System.out.println(Arrays.toString(ans));

        String[] ans = { "1/0"
                , "0/1"
                , "1/2"
                , "2/6"
                , "9/24"
                , "44/120"
                , "265/720"
                , "1854/5040"
                , "14833/40320"
                , "133496/362880"
                , "1334961/3628800"
                , "14684570/39916800"
                , "176214841/479001600"

};



        while(T-->0){

            int n = Integer.parseInt(br.readLine());
//            int[] arr = new int[n];
//            for(int i=0; i<n; i++){
//                arr[i] = i;
//            }
//
//            int count =0;
//
//            do{
//
//                boolean is = true;
//                for(int i=0; i<n; i++){
//                    int cur = arr[i];
//                    if(cur==i){
//                        is = false;
//                        break;
//                    }
//                }
//                if(is) count++;
//
//            }while(nextPermutation(arr));

            System.out.println(ans[n]);

        }
    }

//    static void solve(int n){
//        int[] arr = new int[n];
//        for(int i=0; i<n; i++){
//            arr[i] = i;
//        }
//
//        int count =0;
//
//        do{
//
//            boolean is = true;
//            for(int i=0; i<n; i++){
//                int cur = arr[i];
//                if(cur==i){
//                    is = false;
//                    break;
//                }
//            }
//            if(is) count++;
//
//        }while(nextPermutation(arr));
//
//        ans[n] = String.format("%d/%d\n", count, fac[n]);
//
//    }



    static boolean nextPermutation(int[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
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