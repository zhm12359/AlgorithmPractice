import java.io.*;
import java.util.*;
public class Continents {

    static BufferedReader br;
    static int[][] map;
    static boolean[][] visited;
    static String[][] input;
    static int m;
    static int n;
    static ArrayList<Integer> counts = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(true){
            String line = br.readLine();
            if(line==null) break;
            int[] tks = lineToIntArray(line);
            n= tks[0];
            m = tks[1];


            map = new int[n][m];
            visited = new boolean[n][m];
            input = new String[n][m];
            counts = new ArrayList<>();


            for(int i=0; i<n; i++){
                input[i] = br.readLine().split("");
            }

            tks = lineToIntArray(br.readLine());
            int x = tks[0];
            int y = tks[1];

            String land=input[x][y];


            for(int i=0; i<n; i++){
                String[] row = input[i];
                for(int j=0; j<m; j++){
                    if(row[j].equals(land)) map[i][j] = 1;
                }
            }

            //for(int i=0; i<n; i++) System.out.println(Arrays.toString(map[i]));

            turnOff(x,y);
            visited = new boolean[n][m];

            //for(int i=0; i<n; i++) System.out.println(Arrays.toString(map[i]));

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j]==1) counts.add(bfs(i,j));
                }
            }

            //System.out.println(counts);
            int max=0;
            for(int i=0; i<counts.size(); i++){
                max = Math.max(max, counts.get(i));
            }

            System.out.println(max);
            br.readLine();


        }

    }

    public static void turnOff(int a, int b){

        Queue<Pair> ready = new LinkedList<>();
        Pair o = new Pair(a,b);

        ready.offer(o);

        while(!ready.isEmpty()){

            Pair cur = ready.poll();

            int x = cur.x;
            int y = cur.y;
//
//            System.out.println(x + " " + y);

            if(x<0 || y<0 || x>=n) continue;
            if(visited[x][y]) continue;

            visited[x][y] = true;
            map[x][y]=0;

            //up
            if(x-1 >=0 && map[x-1][y]==1){
                ready.offer(new Pair(x-1, y));
            }
            //down
            if(x+1 <n && map[x+1][y]==1){
                ready.offer(new Pair(x+1,y));
            }
            //left
            if(y-1>=0 && map[x][y-1]==1){
                ready.offer(new Pair(x, y-1));
            }
            //right
            if(y+1<m && map[x][y+1]==1){
                ready.offer(new Pair(x, y+1));
            }

            if(y==0){
                //right
                if(y+1<m&& map[x][y+1]==1){
                    ready.offer(new Pair(x, y+1));
                }
                //left
                if(map[x][m-1]==1){
                    ready.offer(new Pair(x, m-1));
                }
            }else if(y==m-1){

                //left
                if(map[x][y-1]==1){
                    ready.offer(new Pair(x, y-1));
                }
                //right
                if(map[x][0]==1){
                    ready.offer(new Pair(x, 0));
                }
            }

        }


    }

    public static int bfs(int a, int b){
        int count =0;


        Queue<Pair> ready = new LinkedList<>();
        Pair o = new Pair(a,b);

        ready.offer(o);

        while(!ready.isEmpty()){

            Pair cur = ready.poll();

            int x = cur.x;
            int y = cur.y;
//
//            System.out.println(x + " " + y);

            if(x<0 || y<0 || x>=n) continue;
            if(visited[x][y]) continue;

            count++;

            visited[x][y] = true;
            map[x][y]=0;

            //up
            if(x-1 >=0 && map[x-1][y]==1){
                ready.offer(new Pair(x-1, y));
            }
            //down
            if(x+1 <n && map[x+1][y]==1){
                ready.offer(new Pair(x+1,y));
            }
            //left
            if(y-1>=0 && map[x][y-1]==1){
                ready.offer(new Pair(x, y-1));
            }
            //right
            if(y+1<m && map[x][y+1]==1){
                ready.offer(new Pair(x, y+1));
            }

            if(y==0){
                //left
                if(map[x][m-1]==1){
                    ready.offer(new Pair(x, m-1));
                }
            }else if(y==m-1){

                //right
                if(map[x][0]==1){
                    ready.offer(new Pair(x, 0));
                }
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

    public static class Pair  {

        int x;
        int y;

        public Pair(int l, int r){
            this.x = l;
            this.y = r;
        }

    }
}