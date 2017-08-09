import java.io.*;
import java.util.*;
public class Sabotage {

    static BufferedReader br;
    static int[][] graph;
    static int[][] rGraph;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){
            int[] tks = lineToIntArray(br.readLine());
            int n = tks[0], m= tks[1];
            if(n==0 && m==0) break;

            graph = new int[n][n];
            parent = new int[n];
            for(int i=0; i<m; i++){
                tks = lineToIntArray(br.readLine());

                int a = tks[0] -1, b = tks[1] -1, w = tks[2];
                graph[a][b]=w;
                graph[b][a]=w;
            }

            fordFulkerson(0,1);
            System.out.println();


        }
    }


    // Returns tne maximum flow from s to t in the given graph
    static void fordFulkerson(int s, int t)
    {
        int u, v;
        int V = graph[0].length;


        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        rGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Flow is maximum now, find vertices reachable from s
        boolean[] visited = new boolean[V];
        dfs(s, visited);
//
//        System.out.println(Arrays.toString(visited));
//        for(int i=0; i<V; i++) System.out.println(Arrays.toString(rGraph[i]));

        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(visited[i] && !visited[j] && graph[i][j]!=0){
                    System.out.printf("%d %d\n", i+1,j+1);
                }
            }
        }


    }

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    public static boolean bfs(int rGraph[][], int s, int t)
    {
        int V = graph[0].length;
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i]=false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<V; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }


    // A DFS based function to find all reachable vertices from s.  The function
    // marks visited[i] as true if i is reachable from s.  The initial values in
    // visited[] must be false.
    public static void dfs(int s, boolean[] visited){
        int V= graph[0].length;
        visited[s] = true;
        for(int i=0; i<V; i++){
            if(rGraph[s][i]!=0 && !visited[i]){
                dfs(i, visited);
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