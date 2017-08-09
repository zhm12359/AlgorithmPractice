import java.io.*;
import java.util.*;
public class Wetland {

    static BufferedReader br;
    static boolean[][] land;
    static boolean[][] visited;

    public static class Pair  {

        int x;
        int y;

        public Pair(int l, int r){
            this.x = l;
            this.y = r;
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        br.readLine();

        while(T-->0){

            land = new boolean[101][101];


            String line;
            int row = 1;
            int len=0;
            while((line=br.readLine())!=null && !line.equals("")){

                if(isAlpha(line)){


                    String[] ls = line.split("");
                    len = ls.length;
                    for(int i=0; i<ls.length; i++){
                        if(ls[i].equals("W")) land[row][i+1] = true;
                    }
                    row++;
                }else{
                    int[] tks = lineToIntArray(line);
                    int x = tks[0];
                    int y = tks[1];
                    visited = new boolean[101][101];

                    System.out.println(bfs(x,y));

                }


            }
            if(T>0)System.out.println();




//            for(int i=0; i<row; i++){
//                for(int c=0; c<len+1; c++){
//                    System.out.print( land[i][c] +" ");
//                }
//                System.out.println();
//            }






        }

    }

    public static int bfs(int x, int y){

        int count = 0;

        Queue<Pair> ready = new LinkedList<>();

        ready.offer(new Pair(x,y));

        while(!ready.isEmpty()){

            Pair cur = ready.poll();
            count++;
            int r = cur.x;
            int c = cur.y;
            visited[r][c] = true;

            //System.out.printf("r  %d,  c %d\n", r, c);

            if(!visited[r+1][c+1] && land[r+1][c+1]) {
                ready.offer(new Pair(r+1, c+1));//lower right
                visited[r+1][c+1] = true;
            }
            if(!visited[r+1][c-1] && land[r+1][c-1]) {
                ready.offer(new Pair(r+1, c-1));//lower left
                visited[r+1][c-1] = true;
            }
            if(!visited[r-1][c+1] && land[r-1][c+1]) {
                ready.offer(new Pair(r-1, c+1));//upper right
                visited[r-1][c+1] = true;
            }
            if(!visited[r-1][c-1] && land[r-1][c-1]) {
                ready.offer(new Pair(r-1, c-1));//upper left
                visited[r-1][c-1] = true;
            }
            if(!visited[r+1][c] && land[r+1][c]) {
                ready.offer(new Pair(r+1, c)); //below
                visited[r+1][c] = true;
            }
            if(!visited[r-1][c] && land[r-1][c]) {
                ready.offer(new Pair(r-1, c)); //above
                visited[r-1][c] = true;
            }
            if(!visited[r][c-1] && land[r][c-1]) {
                ready.offer(new Pair(r, c-1)); //left
                visited[r][c-1] = true;
            }
            if(!visited[r][c+1] && land[r][c+1]) {
                ready.offer(new Pair(r, c+1)); //right
                visited[r][c+1] = true;
            }

        }



        return count;


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

    //from online..
    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}