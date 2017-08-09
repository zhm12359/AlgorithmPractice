import java.io.*;
import java.util.*;
public class WheresWaldorf {

    static BufferedReader br;
    static String search;
    static char[][] grid;
    static int m,n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            br.readLine();

            int[] tks = lineToIntArray(br.readLine());
            m = tks[0];
            n =tks[1];

            grid = new char[m][n];
            for(int i=0; i<m;i++){
                grid[i] = br.readLine().toLowerCase().toCharArray();
            }

            int K = Integer.parseInt(br.readLine());

            for(int k=0; k<K; k++ ){
                search = br.readLine().toLowerCase();
                //System.out.printf("%s  ", search);

                boolean done = false;
                for(int i=0; i<m; i++){
                    for(int j=0; j<n;j++){
                        if( grid[i][j]==search.charAt(0)&& lookup(i,j)){
                            System.out.printf("%d %d\n",i+1,j+1);
                            done = true;
                            break;
                        }

                    }
                    if(done) break;
                }

            }

            if(T>0) System.out.println();

        }
    }

    public static boolean lookup(int i, int j){



        int len = search.length();

        if(len<=1) return true;

        //up to down
        for(int c=1; c<len && i+c < m && grid[i+c][j] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //down to up
        for(int c=1; c<len && i-c >= 0 && grid[i-c][j] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //left to right
        for(int c=1; c<len && j+c < n && grid[i][j+c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //right to left
        for(int c=1; c<len && j-c >=0 && grid[i][j-c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //to lower right
        for(int c=1; c<len && i+c < m && j+c<n && grid[i+c][j+c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //to lower left
        for(int c=1; c<len && i+c < m && j-c>=0 && grid[i+c][j-c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //to upper left
        for(int c=1; c<len && i-c >= 0 && j-c>=0 && grid[i-c][j-c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }
        //to upper right
        for(int c=1; c<len && i-c >= 0 && j+c<n && grid[i-c][j+c] == search.charAt(c); c++) {
            if(c==len-1) return true;
        }


        return false;
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

