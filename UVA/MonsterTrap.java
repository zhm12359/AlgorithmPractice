import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class MonsterTrap {


    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        while(T-->0){

            int n = Integer.parseInt(br.readLine());
            List<Edge>[] graph = createGraph( n*n*2 );

            int source = 0;
            int sink = 0;

            int numNode = n*n;

            String[] grid = new String[n];
            for(int i=0; i<n; i++){
                grid[i] = br.readLine();
            }
            int id = 0;

            for(int i=0; i<n; i++){
                String line = grid[i];
                for(int j=0; j<n; j++){


                    char cur = line.charAt(j);
                    if(cur=='I'){
                        sink = i*n+j+numNode;
                        addEdge(graph, i*n+j, i*n+j+numNode, 1 );
                        addEdge(graph, i*n+j+numNode, i*n+j, 1 );
                    }

                    if(cur=='M' || cur=='.'){
                        if(cur=='M') source = i*n+j;
                        addEdge(graph, i*n+j, i*n+j+numNode, 1 );
                        addEdge(graph, i*n+j+numNode, i*n+j, 1 );

                    }

                    if(cur!='#'  ){
                        //check up
                        if(i-1>0 && grid[i-1].charAt(j) != '#' ){

                            addEdge(graph, i*n+j+numNode, (i-1)*n+j, 1 );
                            addEdge(graph, (i-1)*n+j+numNode, i*n+j,  1 );

                        }

                        //check down
                        if(i+1<n && grid[i+1].charAt(j) != '#' ){

                            addEdge(graph, i*n+j+numNode, (i+1)*n+j, 1 );
                            addEdge(graph, (i+1)*n+j+numNode, i*n+j , 1 );

                        }

                        //check left
                        if(j-1>0 && grid[i].charAt(j-1) != '#' ){

                            addEdge(graph, i*n+j+numNode, (i)*n+j-1, 1 );
                            addEdge(graph,  (i)*n+j-1+numNode, i*n+j, 1 );


                        }

                        //check right
                        if(j+1<n && grid[i].charAt(j+1) != '#' ){

                            addEdge(graph, i*n+j+numNode, (i)*n+j+1, 1 );
                            addEdge(graph, (i)*n+j+1+numNode,i*n+j,  1 );

                        }


                    }




                }
            }

            int ans = maxFlow(graph, source, sink);
            System.out.println(ans);


        }




    }



    static class Edge {
        int s, t, rev, cap, f;

        public Edge(int s, int t, int rev, int cap) {
            this.s = s;
            this.t = t;
            this.rev = rev;
            this.cap = cap;
        }
    }

    public static List<Edge>[] createGraph(int nodes) {
        List<Edge>[] graph = new List[nodes];
        for (int i = 0; i < nodes; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap) {
//        System.out.println(graph.length);
//        System.out.println(s);
//        System.out.println(t);
        graph[s].add(new Edge(s, t, graph[t].size(), cap));
        graph[t].add(new Edge(t, s, graph[s].size() - 1, 0));
    }

    public static int maxFlow(List<Edge>[] graph, int s, int t) {
        int flow = 0;
        int[] q = new int[graph.length];
        while (true) {
            int qt = 0;
            q[qt++] = s;
            Edge[] pred = new Edge[graph.length];
            for (int qh = 0; qh < qt && pred[t] == null; qh++) {
                int cur = q[qh];
                for (Edge e : graph[cur]) {
                    if (pred[e.t] == null && e.cap > e.f) {
                        pred[e.t] = e;
                        q[qt++] = e.t;
                    }
                }
            }
            if (pred[t] == null)
                break;
            int df = Integer.MAX_VALUE;
            for (int u = t; u != s; u = pred[u].s)
                df = Math.min(df, pred[u].cap - pred[u].f);
            for (int u = t; u != s; u = pred[u].s) {
                pred[u].f += df;
                graph[pred[u].t].get(pred[u].rev).f -= df;
            }
            flow += df;
        }
        return flow;
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

    public static double[] lineToDoubleArray(String line) {
        String[] token = line.split("\\s+");
        int len = token.length;
        double[] res = new double[len];
        for (int i = 0; i < len; ++i) {
            res[i] = Double.parseDouble(token[i]);
        }
        return res;
    }
}
