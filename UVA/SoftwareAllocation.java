
import java.io.*;
import java.util.*;
public class SoftwareAllocation {

    static BufferedReader br;
    static int[][] graph;
    static int[][] rGraph;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        while(true){

            String line;
            graph = new int[38][38]; // 0 is source, [1,26] are applications, [27-36] are computers, 37 is sink.
            parent = new int[38];
            for(int i=27; i<=36; ++i) graph[i][37] =1 ; //computer to sink is 1 (each computer can only process one at a time)

            int targetFlow = 0;
            while((line=br.readLine())!=null && !line.equals("")){

                String[] tks = line.split("\\s+");
                char program = tks[0].charAt(0);
                int weight = Integer.parseInt( tks[0].substring(1) );
                targetFlow += weight;

                graph[0][program - 'A' + 1] = weight; //source to program has weight amount of target flows

                for(char i : tks[1].replace(";","").toCharArray()){
                    //System.out.println(i - '0' + 27);
                    graph[program - 'A' + 1][ i - '0' + 27  ] = 1; // program to computer is 1
                }

            } //end of input -> graph


            char[] result = new char[10];
            Arrays.fill(result, '_');
            if(fordFulkerson(0, 37)==targetFlow){

                for(int i=27; i<=36; i++){
//                    if(parent[i] ==-1) System.out.print("_");
//                    else System.out.print( (char)(parent[i]+'A'-1)+" ");

                    // look for starting from computers to Programs, that has a backedge with 1

                    //boolean found=false;
                    for(int j=1; j<=26; j++){

                        if(rGraph[i][j]==1) {
                            result[i-27] = (char) (j+'A'-1);
                            //System.out.print( (char) (j+'A'-1) );
                        }
                        //found=true;

                    }

                    //if(!found) System.out.print("_");

                }

                System.out.println(String.valueOf(result));

            }else{
                System.out.println("!");
            }

            if(line==null) break;


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