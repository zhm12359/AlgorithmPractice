import java.io.*;
import java.util.*;
public class GridGame {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T;++t){

            int n = Integer.parseInt(br.readLine());

            int[][] board = new int[n][n];
            for(int i=0; i<n; ++i){
                board[i] = lineToIntArray(br.readLine());
            }

            int[] choice = new int[n];
            for(int i=0; i<n; i++) choice[i]=i;

            int sum=0;
            int min = Integer.MAX_VALUE;
            do{
                sum=0;
                for(int i=0; i<n; ++i){
                    sum+=board[i][choice[i]];
                }

                min = Math.min(min, sum);


            }while(nextPermutation(choice));

            System.out.println(min);


        }
        pw.flush();

    }

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