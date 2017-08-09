import java.io.*;
import java.util.*;
public class FactorsAndMultiples {

    static BufferedReader br;

    static int[][] graph;
    static int[][] rGraph;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){

            int[] a = lineToIntArray(br.readLine());
            int[] b = lineToIntArray(br.readLine());



            int n = a[0];
            int m = b[0];

            int[] setA = Arrays.copyOfRange(a,1, n+1);
            int[] setB = Arrays.copyOfRange(b, 1, m+1);


            graph = new int[2+m+n][2+m+n];
            parent = new int[2+m+n];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    int x = setA[i];
                    int y = setB[j];

                    if(x==0&&y==0){
                        graph[i+1][j+n+1] = 1;
                    }

                    if(x!=0 && y%x==0){
                        graph[i+1][j+n+1] = 1;
                    }
                }
            }

            for(int i=0; i<n; i++){
                graph[0][i+1] = 1;
            }

            int counter =0;
            for(int j=n+1; counter<m; counter++){
                graph[j][m+n+1] = 1;
                j++;
            }

            int maxFlow = fordFulkerson(0, 1+m+n);

            System.out.printf("Case %d: %d\n", t, maxFlow);


        }
    }

    static int fordFulkerson(int s, int t)
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

        // Return the overall flow
        return max_flow;
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