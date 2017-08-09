import java.io.*;
import java.util.*;
public class StreetDirections2 {

    static BufferedReader br;
    static TreeMap<Integer, TreeSet<Integer>> graph;
    static int[] dfs_num;
    static int[] dfs_low;
    static int dsfNumberCounter=0;
    static int[] dfs_parent;
    static boolean[] visited;
    static HashSet<String> backedges;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int casenum=0;
        while(true) {


            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0];
            int m = tks[1];
            if (n == 0 && m == 0) break;
            graph = new TreeMap<>();
            dfs_num = new int[n+1];
            dfs_low = new int[n+1];
            dfs_parent = new int[n+1];
            visited = new boolean[n+1];
            backedges = new HashSet<>();
            dsfNumberCounter = 0;
            for (int i = 1; i <= n; i++) {
                graph.put(i, new TreeSet<>());
                dfs_parent[i] = Integer.MIN_VALUE;
                visited[i] = false;
            }

            for (int i = 0; i < m; i++) {

                tks = lineToIntArray(br.readLine());
                int a = tks[0];
                int b = tks[1];

                graph.get(a).add(b);
                graph.get(b).add(a);

            }

            //System.out.println(graph);

            casenum++;
            System.out.println(casenum+"\n");

            for(int i : graph.keySet()){
                if(!visited[i]){
                    bridge(i);
                }
            }
            System.out.println("#");




        }

    }

    public static void bridge(int u){

        visited[u] = true;
        dfs_num[u] = dfs_low[u] = ++dsfNumberCounter;

        for(int v: graph.get(u)){
            if(!visited[v]){
                dfs_parent[v]=u;
                System.out.printf("%d %d\n", u, v);
                bridge(v);



                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);

                if(dfs_low[v]>dfs_num[u]){
                    //System.out.printf("Edge (%d %d) is a bridge\n", u, v);
                    System.out.printf("%d %d\n", v, u);
                }

            }else if(v!=dfs_parent[u]){
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
                if(!backedges.contains(v+"---"+u)){
                    backedges.add(u+"---"+v);
                    System.out.printf("%d %d\n", u, v);
                }

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