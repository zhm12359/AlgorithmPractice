import java.io.*;
import java.util.*;
public class StreetDirections {

    static BufferedReader br;
    static TreeMap<Integer, TreeSet<Integer>> graph;
    static int[] dfs_num;
    static int[] dfs_low;
    static boolean[] articulationPoint;
    static int dsfNumberCounter=0;
    static int[] dfs_parent;
    static boolean[] visited;
    static int dfsRoot;
    static int rootChildren;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){
            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0];
            int m = tks[1];
            if(n==0 && m==0) break;
            graph = new TreeMap<>();
            dfs_num = new int[n+1];
            dfs_low = new int[n+1];
            dfs_parent = new int[n+1];
            visited = new boolean[n+1];
            articulationPoint = new boolean[n+1];
            dsfNumberCounter=0;
            for(int i=0; i<n; i++) {
                graph.put(i, new TreeSet<>());
                dfs_parent[i] = -1;
                visited[i] = false;
            }

            for(int i=0; i<m; i++){

                tks = lineToIntArray(br.readLine());
                int a = tks[0];
                int b = tks[1];

                graph.get(a).add(b);
                graph.get(b).add(a);

            }

            System.out.println(graph);

            for(int i:graph.keySet()){
                if(visited[i]==false){
                    dfsRoot=i;
                    rootChildren=0;
                    articulationPointAndBridge(i);
                    System.out.println("done"+i);
                    articulationPoint[dfsRoot] = (rootChildren>1);
                }
            }

            System.out.println("dfs_num"+" "+ Arrays.toString(dfs_num));
            System.out.println("dfs_low"+" "+ Arrays.toString(dfs_low));
            System.out.println("Articulation Points:");
            for(int i=0; i<n; i++){
                if(articulationPoint[i]){
                    System.out.println(i);
                }
            }


            System.out.println("\n\n");

        }
    }

    public static void articulationPointAndBridge(int u){
        dfs_num[u] = dfs_low[u]  = ++dsfNumberCounter;
        visited[u]=true;

        for(int v: graph.get(u)){
            if(!visited[v]){
                dfs_parent[v]=u;
                if(u==dfsRoot) rootChildren++;
                articulationPointAndBridge(v);

                if(dfs_low[v]>=dfs_num[u]){
                    articulationPoint[u] = true;
                }

                if(dfs_low[v] > dfs_num[u]){
                    System.out.printf("Edge (%d %d) is a bridge\n", u, v);
                }

                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
            }else if(v !=dfs_parent[u]){
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
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