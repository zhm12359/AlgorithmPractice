import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
public class EightQueen {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String l = br.readLine();


        //preprocess eight queen results
        ArrayList<int[]> ans = new ArrayList<>();


        int[] arr = {1,2,3,4,5,6,7,8};

        do{

            if(checkDiagonal(arr)) {
                int[] good= arr.clone();
                ans.add(good);
            }

        }while(nextPermutation(arr));



        int cases=0;
        while(l!=null){
            cases+=1;

            String[] line= l.split(" ");

            int[] board = new int[8];
            for(int i=0; i<8; ++i){
                board[i] = Integer.parseInt(line[i]);
            }

            int minMove = 8;
            for(int i=0; i<92; ++i){
                int[] cur = ans.get(i);


                minMove = Math.min(findDiff(cur, board), minMove);
            }


            pw.printf("Case %d: %d\n" ,cases, minMove);
            l = br.readLine();

        }
        pw.flush();

    }

    public static int findDiff(int[] a1, int[]a2){

        int diff=0;
        for(int i=0; i<8; ++i){
            if(a1[i]!=a2[i]) diff++;
        }
        return diff;
    }


    public static boolean checkDiagonal(int[] board){

        ArrayList<Integer> seen = new ArrayList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        for(int i=0; i<7; ++i){

            int row1 = board[i];
            int col1 = i;

            for(int j=i+1; j<8; ++j){

                int row2 = board[j];
                int col2 = j;

                if(Math.abs(row2-row1) == Math.abs(col2-col1)){
                    return false;
                }

            }

        }

        return true;
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


}