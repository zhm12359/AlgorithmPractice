import java.io.*;
import java.util.*;
public class AngryProgrammer {

    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        while(true){
            int[] tks = lineToIntArray(br.readLine());
            int m = tks[0], w= tks[1];
            if(m==0 && w==0) break;

            int boss = 0;
            int center = m-1;

            List<Edge>[] graph = createGraph( m*2 );
            addEdge(graph, boss, boss+m, 10000000);
            addEdge(graph, center, center+m, 10000000);

            for(int i=0; i<m-2; i++){
                tks = lineToIntArray(br.readLine());
                int machine = tks[0]-1;
                int weight = tks[1];
                addEdge(graph, machine, machine+m, weight);
            }
            for(int i=0; i<w; i++){
                tks = lineToIntArray(br.readLine());
                int from = tks[0]-1;
                int to = tks[1]-1;
                int weight = tks[2];

                addEdge(graph, from+m, to, weight);
                addEdge(graph, to+m, from, weight);

            }


//            List<Edge>[] graph = createGraph((m-2)*2 + 4 );
//
//
//            int sink = graph.length-1;
//            int center = sink-1;
//
//            for(int i=0; i<m-2; i++){
//                tks = lineToIntArray(br.readLine());
//                int machine = tks[0];
//                int weight = tks[1];
//                //graph[machine][machine + (m-2)] = weight;
//                addEdge(graph, machine, machine+(m-2), weight);
//            }
//
//            for(int i=0; i<w; i++){
//                tks = lineToIntArray(br.readLine());
//                int from = tks[0];
//                int to = tks[1];
//                int weight = tks[2];
//
//                if(from==1 && to ==m){
////                    graph[from][center]=weight;
////                    graph[center][from] = weight;
//                    addEdge(graph, from, center, weight);
//                    addEdge(graph, center, from, weight);
//
//                }else if(from==1) {
////                    graph[from][to] = weight;
////                    graph[to +m -2][from] = weight;
//                    addEdge(graph, from, to, weight);
//                    addEdge(graph, to+m-1, from, weight);
//                }else if(to==m){
////                    graph[from+m-2][center]=weight;
//                    addEdge(graph, from+m-2, center, weight);
//                }else{
////                    graph[from+m-2][to] = weight;
////                    graph[to+m-2][from] = weight;
//                    addEdge(graph, from+m-2, to, weight);
//                    addEdge(graph, to+m-2, from, weight);
//                }
//            }


            //for(int i=0; i<graph[0].length; i++) System.out.println(Arrays.toString(graph[i]));

            System.out.println(maxFlow(graph, boss, center));





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

}