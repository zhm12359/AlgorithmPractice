import java.io.*;
import java.util.*;


public class Bulbs{
    static BufferedReader br;
    static int r;
    static int c;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {

            int[] tokens = lineToIntArray(br.readLine());
            r = tokens[0];
            c = tokens[1];
            int B = tokens[2];

            int[][] board = new int[r][c];
            for(int i=0; i<r; ++i){
                String[] row = br.readLine().split("");
                for(int j=0; j<c;++j){
                    if(row[j].equals("*")) board[i][j]=1;
                }
            }

            // complete search on rows
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < (1 << r); j++) {
                int cost=0;

                for (int k = 0; k < r; k++) {
                    if ((j & (1 << k)) != 0) { //use kth element in this mask j
                        ++cost; //row cost
                    }
                }


                int total=0;
                ArrayList<Integer> actions = new ArrayList<>();
                for(int m=0; m<c; ++m){
                    int bulb=0;
                    for(int i=0; i<r; ++i){
                        if( (board[i][m] + (j>>i)&1 )%2 ==1 ){
                            bulb++;
                        }
                    }
                    total+=bulb;
                    int change = bulb - (r-bulb);
                    if(change>0) actions.add(change);
                }

                Collections.sort(actions, Collections.reverseOrder());

                int len=actions.size();
                for(int i=0; i<len; ++i){
                    if(total<=B) break;
                    total -= actions.get(i);
                    cost++;

                }

                if(total<=B) min = Math.min(min, cost);

            }

            pw.println(min==Integer.MAX_VALUE? -1: min);
            pw.flush();


        }
    }

    public static int[] lineToIntArray(String line){
        String[] token = line.split("\\s+");
        int len = token.length;
        int[] res = new int[len];
        for(int i=0; i<len; ++i ){
            res[i] = Integer.parseInt(token[i]);
        }
        return res;
    }

}



/*

for(int mrow = 0; mrow < (1<<N); mrow++) {
        int cost = 0;
        for(int i = 0; i < N; i++) {
            cost += (mrow >> i) & 1;
        }
        int tot = 0;
        ArrayList<Integer> actions = new ArrayList<Integer>();
        for(int j = 0 ; j < M; j++) {
            int bulb = 0;
            for(int i = 0; i < N; i++) {
                if((g[j][i] + ((mrow >> i) & 1)) % 2 == 1)
                    bulb++;
            }
            tot += bulb;
            int action = bulb - (N - bulb);
            if(action > 0)
                actions.add(action);
        }
        Collections.sort(actions, Collections.reverseOrder());
        int len = actions.size();
        for(int i= 0; i < len; i++) {
            if(tot <= B) break;
            cost++;
            tot -= actions.get(i);
        }
        if(tot <= B) ans = Math.min(ans, cost);
    }
    System.out.println( ans = 1000000 ? "-1" : ans);
public class Bulbs {

    static BufferedReader br;
    static String[][] board;
    static int r;
    static int c;
    static int[] rowColOn;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; ++t){

            int[] tokens = lineToIntArray(br.readLine());
            r = tokens[0];
            c = tokens[1];
            int B = tokens[2];

            rowColOn = new int[r+c];
            //int[] colOn = new int[c];


            board = new String[r][c];
            for(int i=0; i<r; i++){
                board[i] = br.readLine().split("");

                int rowSum =0;
                for(int j=0; j<c; ++j){

                    if(board[i][j].equals("*")){
                        rowSum++;
                        rowColOn[j+r]++;
                    }
                    rowColOn[i] = rowSum;
                }
            }

            int cost=0;
            int min=Integer.MAX_VALUE;
            for (int j = 0; j < (1 << r); j++) {
                cost=0;
                int totalCost = 0;
                int totalFlow = 0;
                for (int k = 0; k < r; k++) {
                    if ((j & (1 << k)) != 0) { //use kth element in this mask j
                        toggleRow(k);
                        ++cost;
                    }
                }

                int tryCol=0;
                while(tryCol<c){
                    int col = findMax();
                    tryCol++;
                    toggleCol(col);
                    if(countOn()<=B) break;
                }
                cost+=tryCol;
                if(countOn()>B) cost=Integer.MAX_VALUE;
                min=Math.min(cost,min);

            }





            pw.println();

//            if(prev!=numOn) pw.println(count);
//            else pw.println(-1);


//            pw.println(Arrays.toString(rowColOn));
//            toggleCol(rowColOn, 8 );
//            toggleCol(rowColOn, 15 );
//            pw.println(Arrays.toString(rowColOn));
            //pw.println(Arrays.toString(colOn));
            pw.flush();


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

    public static int findMax(){

        int res=0;
        int max=-1;
        for(int i=r; i<rowColOn.length; ++i){
            if(rowColOn[i]>max){
                max=rowColOn[i];
                res = i;
            }
        }

        return res;
    }

    public static int countOn(){

        int sum=0;
        for(int i=0; i<r; ++i){
            sum+=rowColOn[i];
        }
        return sum;
    }

    public static void toggleRow( int ridx){

        String[] row = board[ridx];

        for(int i=0; i<r; ++i){

            if(row[i].equals("*")){
                row[i] = ".";
                rowColOn[ridx]--;
                rowColOn[i+r]--;
            }else{
                row[i]="*";
                rowColOn[ridx]++;
                rowColOn[i+r]++;
            }
        }
    }

    public static void toggleCol(int cidx){

        int j = cidx-r;
        for(int i=0; i<r; ++i){

            if(board[i][j].equals("*")){
                rowColOn[cidx]--;
                rowColOn[i] --;
                board[i][j]=".";
            }else{
                rowColOn[cidx]++;
                rowColOn[i] ++;
                board[i][j]="*";
            }

        }

    }


//complete search on the rows??

}
*/