import java.io.*;
import java.util.*;
public class Vertex {

    static BufferedReader br;
    static TreeMap<Integer, TreeSet<Integer>> adj;

    static boolean[] visited;

    static int count=0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){

            int N = Integer.parseInt(br.readLine());
            if(N==0) break;

            adj = new TreeMap<>();

            for(int i=1; i<N+1; i++){
                adj.put(i, new TreeSet<>());
            }

            //setting up the graph
            String line;
            while(!(line=br.readLine()).equals("0")){

                //System.out.println(line);

                int[] arr = lineToIntArray(line);
                int i=1;
                while(true){
                    if(arr[i]==0) break;
                    adj.get(arr[0]).add(arr[i]);
                    i++;
                }
            }
            //System.out.println(adj);


            int[] query = lineToIntArray(br.readLine());
            int q = query[0];


            for(int i=1; i<q+1; ++i){
                //System.out.printf("case %d\n", i);
                int start = query[i];
                visited = new boolean[N+1];
                count=0;
                dfs(start);


                System.out.print(N-count);
                for(int k=1; k<=N; ++k){
                    if(!visited[k]){
                        System.out.print(" "+k );
                    }
                }


                System.out.print("\n");
            }

        }


    }

    public static void dfs(int n){
        TreeSet<Integer> children = adj.get(n);


        for(int u:children){
            if(!visited[u]){
                visited[u] = true;
                count++;
                dfs(u);
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