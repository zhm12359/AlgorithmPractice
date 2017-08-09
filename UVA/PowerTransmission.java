import java.io.*;
import java.util.*;
public class PowerTransmission {

    static BufferedReader br;
    static int[][] graph;
    static int[][] rGraph;
    static int[] parent;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        String line;
        while((line=br.readLine())!=null){

            int n = Integer.parseInt(line);
            int[] capacities = lineToIntArray(br.readLine());

            graph = new int[2*n+2][2*n+2];
            parent = new int[2*n+2];
            for(int i=1; i<n+1; i++){
                graph[i][i+n] = capacities[i-1]; //change the capacities to edges
            }


            int m = Integer.parseInt(br.readLine());
            for(int i=0; i<m; i++){
                int[] tks = lineToIntArray(br.readLine());
                int a = tks[0], b = tks[1], w= tks[2];
                graph[a+n][b] = w;
                //graph[b+n][a] = w;
            }


            int[] tks = lineToIntArray(br.readLine());
            int barisal = tks[0], dhka = tks[1];

            int[] nodes = lineToIntArray(br.readLine());

            for(int i=0; i<barisal; i++){
                graph[0][nodes[i]] = Integer.MAX_VALUE;
            }
            for(int j=barisal; j<nodes.length; j++){
                graph[nodes[j]+n][2*n+1] = Integer.MAX_VALUE;
            }

            //for(int i=0; i< 2*n+2; i++) System.out.println(Arrays.toString(graph[i]));

            int max = fordFulkerson(0, 2*n+1);
            System.out.println(max);


        }

    }

    //Max flow algo from: http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

    // Returns tne maximum flow from s to t in the given graph
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